//Types of Vehicle that the parking accepts and their start price
enum class VehicleType(val startPrice : Int) {
    CAR(20), MOTORCYCLE(15), MINIBUS(25), BUS(30)
}