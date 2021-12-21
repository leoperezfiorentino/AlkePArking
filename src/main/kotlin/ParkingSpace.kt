import java.util.*

class ParkingSpace {
    lateinit var vehicle: Vehicle
    lateinit var checkInTime : Calendar
    var parkedVehicle: Boolean = false

    fun checkInVehicle(vehicle : Vehicle) : Boolean {
        this.vehicle = vehicle
        checkInTime = Calendar.getInstance()
        parkedVehicle = true
        return true
    }

    fun checkOutVehicle(plate: String, onSuccess: () -> Unit, onError: () -> Unit ) {
        this.vehicle = null

    }

    val parkedTime: Long
        get() = (Calendar.getInstance().timeInMillis - checkInTime.timeInMillis) / 60000
}