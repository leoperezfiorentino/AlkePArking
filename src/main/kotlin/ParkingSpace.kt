import java.util.*

//Class that represents a space for a vehicle in the Parking
class ParkingSpace {
    var vehicle: Vehicle? = null
    private lateinit var checkInTime : Calendar
    var parkedVehicle: Boolean = false

    //Function that receives a vehicle and assign it to the Parking Space
    fun addVehicle(vehicle : Vehicle) : Boolean {
        this.vehicle = vehicle
        this.checkInTime = Calendar.getInstance()
        this.parkedVehicle = true
        return true
    }

    //Function that takes the final fee to pay and withdraw the vehicle from the Parking Space
    fun removeVehicle(finalFee: Int, onSuccess: (Int) -> Unit) {
            this.vehicle = null
            this.parkedVehicle = false
            onSuccess(finalFee)
    }

    //Constant that store the total time that a vehicle stayed in the Parking Space in milliseconds
    val parkedTime: Double
        get() = ((Calendar.getInstance().timeInMillis.toDouble() + 11580000 - checkInTime.timeInMillis.toDouble()) / 60000)
}