import kotlin.math.ceil
import kotlin.math.roundToInt

//Class that make a Set of vehicles and set the max spaces to 20 by default
data class Parking(val vehicles: MutableSet<Vehicle>, val maxSpaces: Int = 20) {
    //Implements a Set because it can't be repeated
    private val parkingSpaces : MutableList<ParkingSpace> = mutableListOf()
    private var earnings: Pair<Int, Int> = Pair(0,0)

    //Initializes the spaces in the parking according to the maxSpaces value
    init {
        for(i in 1 .. maxSpaces) {
            parkingSpaces.add(ParkingSpace())
        }

    }

    //Function that check-in a vehicle in the Parking Space if this is not already parked
    fun checkInVehicle(vehicle: Vehicle) : Boolean {
        if(alreadyParked(vehicle)) {
            println("Vehicle already parked.")
            return false
        }
        parkingSpaces.forEach {
            if(!it.parkedVehicle) {
                it.addVehicle(vehicle)
                return true
            }
        }
        return false
    }

    /*
        Function that:
                    check-out a vehicle,
                    calculate their fee,
                    if it has a discount card
                    and remove it from the Parking Space
     */
    fun checkOutVehicle(vehicle: Vehicle) {
        try {
            parkingSpaces.forEach {
                if(it.parkedVehicle) {
                    if(it.vehicle!! == vehicle) {
                        val type = it.vehicle?.type
                        val time = it.parkedTime
                        val fee = calculateFee(type!!, time)
                        var discount = 0
                        if(it.vehicle?.discountCard!!.isNotEmpty()) {
                            discount = (fee*0.15).roundToInt()
                        }
                        val finalFee = fee - discount
                        it.removeVehicle(finalFee, ::onSuccess)
                    }
                }
            }
        }
        catch (error: Exception) {
            onError()
        }
    }

    //Function that print each vehicle parked in the moment of execution
    fun listVehicles() {
        println("\nPlates in parking:")
        parkingSpaces.forEach {
            if(it.vehicle != null) println("${it.vehicle?.plate}")
        }
    }

    //Function that check if a vehicle is already parked
    private fun alreadyParked(vehicle: Vehicle) : Boolean {
        val occupied = parkingSpaces.takeWhile { it.parkedVehicle }
        occupied.forEach {
            if(it.vehicle!! == vehicle) return true
        }
        return false
    }

    //Function that calculate the fee based in the time that a vehicle parked
    private fun calculateFee(vehicleType : VehicleType, parkedTime: Double) : Int {
        return when {
            parkedTime <= 120 -> vehicleType.startPrice
            else -> {
                val fraction = (parkedTime - 120) / 15
                val calculateExtra = ceil(fraction).toInt() * 5
                vehicleType.startPrice + calculateExtra
            }
        }
    }

    //Function that print the final fee price and update the pair of vehicles retires and the total earnings
    private fun onSuccess(price: Int) {
        earnings = Pair(earnings.first + 1, earnings.second + price)
        println("Your fee is $$price. Come back soon.")
    }

    //Function that print an error message
    private fun onError() {println("Sorry, the check-out failed")}

    //Function that print the vehicles checked out and the total earnings
    fun totalEarnings() {
        println("${earnings.first} vehicles have checked out and have earnings of $${earnings.second}")
    }

}
