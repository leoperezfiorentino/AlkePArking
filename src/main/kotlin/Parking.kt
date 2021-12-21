import kotlin.math.ceil

data class Parking(val vehicles: MutableSet<Vehicle>, val maxSpaces: Int = 20) {
    //Implements a Set because it can't be repeated

    val parkingSpaces : MutableList<ParkingSpace> = mutableListOf()

    init {
        for(i in 1 .. maxSpaces) {
            parkingSpaces.add(ParkingSpace())
        }

    }

    fun checkInVehicle(vehicle: Vehicle) : Boolean {
        if(alreadyParked(vehicle)) {
            onError()
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

    fun checkOutVehicle(vehicle: Vehicle) {
        parkingSpaces.forEach {
            if(it.parkedVehicle) {
                if(it.vehicle!!.equals(vehicle)) {
                    val type = it.vehicle?.type
                    val time = it.parkedTime
                    val fee = calculateFee(type!!, time)
                    it.removeVehicle(vehicle.plate, fee, ::onSuccess, ::onError)
                }
            }
        }
    }

    fun alreadyParked(vehicle: Vehicle) : Boolean {
        val occupied = parkingSpaces.takeWhile { it.parkedVehicle }
        occupied.forEach {
            if(it.vehicle!!.equals(vehicle)) return true
        }
        return false
    }

    fun calculateFee(vehicleType : VehicleType, parkedTime: Long) : Int {
        when {
            parkedTime <= 7200000 -> return vehicleType.startPrice
            else -> {
                val calculateExtra = Math.ceil(((parkedTime - 7200000) / 900000).toDouble()).toInt() * 5
                println(calculateExtra)
                return vehicleType.startPrice + calculateExtra
            }
        }
        return -1
    }

    fun onSuccess(price: Int) {
        println("The total price is $price")
    }

    fun onError() {println("ERROR!!")}

}
