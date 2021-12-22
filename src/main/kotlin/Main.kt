fun main() {

    //-----------------------------------
    //Creation of the vehicles to insert
    val car = Vehicle("AA111AA", VehicleType.CAR, "DISCOUNT_CARD_001")
    val moto = Vehicle("BB222BB", VehicleType.MOTORCYCLE)
    val miniBus = Vehicle("CC333CC", VehicleType.MINIBUS, "DISCOUNT_CARD_002")
    val bus = Vehicle("DD444DD", VehicleType.BUS)

    val car2 = Vehicle("EE555EE", VehicleType.CAR, "DISCOUNT_CARD_003")
    val moto2 = Vehicle("FF666FF", VehicleType.MOTORCYCLE)
    val miniBus2 = Vehicle("GG777GG", VehicleType.MINIBUS, "DISCOUNT_CARD_004")
    val bus2 = Vehicle("HH888HH", VehicleType.BUS)

    val car3 = Vehicle("II999II", VehicleType.CAR, "DISCOUNT_CARD_005")
    val moto3 = Vehicle("JJ111JJ", VehicleType.MOTORCYCLE)
    val miniBus3 = Vehicle("KK222KK", VehicleType.MINIBUS, "DISCOUNT_CARD_006")
    val bus3 = Vehicle("LL333LL", VehicleType.BUS)

    val car4 = Vehicle("ZZ027ZZ", VehicleType.CAR)
    val moto4 = Vehicle("YY026YY", VehicleType.MOTORCYCLE)
    val miniBus4 = Vehicle("XX025XX", VehicleType.MINIBUS)
    val bus4 = Vehicle("WW024WW", VehicleType.BUS)

    val car5 = Vehicle("VV023VV", VehicleType.CAR)
    val moto5 = Vehicle("UU022UU", VehicleType.MOTORCYCLE)
    val miniBus5 = Vehicle("TT021TT", VehicleType.MINIBUS)
    val bus5 = Vehicle("SS020SS",VehicleType.BUS)

    val car6 = Vehicle("VV023FF", VehicleType.CAR)
    val moto6 = Vehicle("UU022FF", VehicleType.MOTORCYCLE)
    val miniBus6 = Vehicle("TT021FF", VehicleType.MINIBUS)
    val bus6 = Vehicle("SS020FF",VehicleType.BUS)
    //-----------------------------------

    //-----------------------------------
    //Vehicles being inserted in the parking set
    val parking = Parking(mutableSetOf(
        car, moto, miniBus, bus,
        car2, moto2, miniBus2, bus2,
        car3, moto3, miniBus3, bus3,
        car4, moto4, miniBus4, bus4,
        car5, moto5, miniBus5, bus5,
        car6, moto6, miniBus6, bus6,
        ))
    //-----------------------------------

    //-----------------------------------
    //Check-in and confirmation of vehicles
    parking.vehicles.forEach {
        if(parking.checkInVehicle(it)) println("Welcome to AlkeParking!")
        else println("Sorry, the check-in failed")
    }
    //-----------------------------------

    //-----------------------------------
    //Test of an repeated vehicle insertion
    val car7 = Vehicle("AA111AA", VehicleType.CAR, "DISCOUNT_CARD_001")
    parking.checkInVehicle(car7)
    //-----------------------------------

    //-----------------------------------
    //Check out of a Vehicle and Test of trying to check out a vehicle that you already checked out
    parking.checkOutVehicle(car)
    parking.checkOutVehicle(car)
    //-----------------------------------

    parking.checkOutVehicle(moto)

    //-----------------------------------
    //Print of total earnings and list of vehicles plate parked
    parking.totalEarnings()
    parking.listVehicles()
}