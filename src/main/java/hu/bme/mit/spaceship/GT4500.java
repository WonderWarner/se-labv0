package hu.bme.mit.spaceship;

/**
* A simple spaceship with two proton torpedo stores and four lasers
*/
public class GT4500 implements SpaceShip {

  private TorpedoStore primaryTorpedoStore;
  private TorpedoStore secondaryTorpedoStore;

  private boolean wasPrimaryFiredLast = false;

  public GT4500() {
    this.primaryTorpedoStore = new TorpedoStore(10);
    this.secondaryTorpedoStore = new TorpedoStore(10);
  }

  public boolean fireLaser(FiringMode firingMode) {
    throw Exception();
    return false;
  }


  @Override
  public boolean fireTorpedo(FiringMode firingMode) {

    boolean firingSuccess = false;

    switch (firingMode) {
      case SINGLE:
        if (wasPrimaryFiredLast) {
          // try to fire the secondary first
          if (! secondaryTorpedoStore.isEmpty()) {
            firingSuccess = secondaryTorpedoStore.fire(1);
            wasPrimaryFiredLast = false;
          }
          else {
            // although primary was fired last time, but the secondary is empty
            // thus try to fire primary again
            if (! primaryTorpedoStore.isEmpty()) {
              firingSuccess = primaryTorpedoStore.fire(1);
              wasPrimaryFiredLast = true;
            }

            // if both of the stores are empty, nothing can be done, return failure
          }
        }
        else {
          // try to fire the primary first
          if (! primaryTorpedoStore.isEmpty()) {
            firingSuccess = primaryTorpedoStore.fire(1);
            wasPrimaryFiredLast = true;
          }
          else {
            // although secondary was fired last time, but primary is empty
            // thus try to fire secondary again
            if (! secondaryTorpedoStore.isEmpty()) {
              firingSuccess = secondaryTorpedoStore.fire(1);
              wasPrimaryFiredLast = false;
            }

            // if both of the stores are empty, nothing can be done, return failure
          }
        }
        break;

      case ALL:
        return -1;
    }

    return firingSuccess;
  }

}
