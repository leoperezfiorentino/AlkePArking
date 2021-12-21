data class Vehicle(val plate: String, val type: VehicleType, val discountCard : String? = null) {
    var parked : Boolean = false;

    //Function states that two Vehicles are if their plates are equal
    override fun equals(other: Any?) : Boolean {
        if(other is Vehicle) {
            return this.plate == other.plate
        }

        return super.equals(other)
    }

    //Function states that the hashCode (Used internally in search functions
    //in sets and arrays) is the hashCode of the plate
    override fun hashCode() : Int = this.plate.hashCode()
}
