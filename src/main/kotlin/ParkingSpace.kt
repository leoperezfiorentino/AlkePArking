import java.util.*

class ParkingSpace {
    var vehicle: Vehicle? = null
    lateinit var checkInTime : Calendar
    var parkedVehicle: Boolean = false

    fun addVehicle(vehicle : Vehicle) : Boolean {
        this.vehicle = vehicle
        checkInTime = Calendar.getInstance()
        parkedVehicle = true
        return true
    }

    fun removeVehicle(plate: String, fee: Int, onSuccess: (Int) -> Unit, onError: () -> Unit ) {
        this.vehicle = null
        parkedVehicle = false
        onSuccess(fee)
    }



    val parkedTime: Long
        get() = (Calendar.getInstance().timeInMillis - checkInTime.timeInMillis) / 60000
}