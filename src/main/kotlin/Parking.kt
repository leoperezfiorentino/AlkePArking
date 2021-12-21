data class Parking(val vehicles: MutableSet<Vehicle>, val maxSpaces: Int = 20) {
    //Implements a Set because it can't be repeated

    val parkingSpaces : MutableList<ParkingSpace> = mutableListOf()

    init {
        for(i in 1 .. maxSpaces) {
            parkingSpaces.add(ParkingSpace())
        }

    }

    fun addVehicle(vehicle: Vehicle) : Boolean {
        parkingSpaces.forEach {
            if(!it.parkedVehicle) {
                it.checkInVehicle(vehicle)
                return true
            }
        }
        return false
    }

    fun removeVehicle(vehicle: Vehicle) {
        parkingSpaces.forEach {
            if(it.parkedVehicle) {
                if(it.vehicle.equals(vehicle)) {
                    it.checkOutVehicle(vehicle.plate, ::onSuccess, ::onError)
                    onSuccess()
                }
            }
        }
        onError()
    }

    fun onSuccess() {println("SUCCES!!")}

    fun onError() {println("ERROR!!")}

}
