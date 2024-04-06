import java.util.*;

import models.ClassificationLevel;
import models.MilitaryType;
import org.testng.Assert;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

// version: 1.1
// made by Vitali Shulha
// 4-Jan-2019

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    public List<PassengerPlane> getPassengersPlanes() {
        List<PassengerPlane> passengerPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof PassengerPlane)
                passengerPlanes.add((PassengerPlane) plane);
        }
        return passengerPlanes;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengersPlanes();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
        for (PassengerPlane passengerPlane : passengerPlanes) {
            if (passengerPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity())
                planeWithMaxCapacity = passengerPlane;
        }
        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof MilitaryPlane)
                militaryPlanes.add((MilitaryPlane) plane);
        }
        return militaryPlanes;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();
        for (MilitaryPlane plane : getMilitaryPlanes()) {
            if (plane.getType() == MilitaryType.TRANSPORT)
                transportMilitaryPlanes.add(plane);
        }
        return transportMilitaryPlanes;
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = new ArrayList<>();
        for (MilitaryPlane plane : getMilitaryPlanes()) {
            if (plane.getType() == MilitaryType.BOMBER)
                bomberMilitaryPlanes.add(plane);
        }
        return bomberMilitaryPlanes;
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof ExperimentalPlane)
                experimentalPlanes.add((ExperimentalPlane) plane);
        }
        return experimentalPlanes;
    }

    public Airport sortByMaxDistance() {
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.getMaxFlightDistance() - o2.getMaxFlightDistance();
            }
        });
        return this;
    }

    public Airport sortByMaxSpeed() {
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.getMaxSpeed() - o2.getMaxSpeed();
            }
        });
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.getMaxLoadCapacity() - o2.getMaxLoadCapacity();
            }
        });
        return this;
    }

    public boolean checkExperimentalPlanesHasClassificationLevelHigherThanUnclassified(){
        for(ExperimentalPlane experimentalPlane : getExperimentalPlanes()){
            if(ClassificationLevel.UNCLASSIFIED == experimentalPlane.getClassificationLevel())
                return false;
        }
        return true;
    }

    public boolean checkHasAtLeastOneBomberInMilitaryPlanes(){
        for (MilitaryPlane militaryPlane : getBomberMilitaryPlanes()) {
            if(MilitaryType.BOMBER == militaryPlane.getType())
                return true;
        }
        return false;
    }

    private void print(Collection<? extends Plane> collection) {
        for (Plane plane : planes) {
            System.out.println(plane);
        }
    }

    @Override
    public String toString() {
        return "Airport{" + "Planes=" + planes.toString() + "}";
    }
}
