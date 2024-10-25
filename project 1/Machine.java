public class Machine {
    private final Object kind; // Type of shape as Object
    private final Object[] properties; // Properties of the shape as an array of Object
    private final boolean humanConstrained; // Stores the result of the humanoid check
    private boolean humanEmergence; // Stores the result of the humanoid check

    public Machine(Object kind, Object[] partStates, boolean humanConstrained) {
        //Constructor to initialize objects
        this.kind = kind;
        this.humanConstrained = humanConstrained;
        this.properties = partStates;
        int i;

        //Put PartState objects for property and value 
        for (i = 0; i < partStates.length; ++i) {
            this.properties[i] = partStates[i];
        }
    }

    public Object[] getProperties() {
        //Simple getter method
        return properties;
    }

    public void emergeFromLimitations() {
        humanEmergence = true;
    }

    public boolean isHumanoid() {
        return humanConstrained || humanEmergence;
    }

    @Override
    public String toString() {
        return String.format("Machine{kind=%s, humanoid=%s, properties=%s}", kind, humanConstrained,
                propertiesToString(properties));
    }

    public static String propertiesToString(Object[] machineProperties) {
        //Create string
        String result = "";
        for (Object property:machineProperties) {
            if (property != null) {
                result += property.toString() + ',';
            }
        }
        //Takes out trailing space
        if (result.isEmpty()) {
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }
}
