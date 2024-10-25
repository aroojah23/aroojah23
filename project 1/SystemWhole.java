public class SystemWhole {
    public static String[] emergences; // To store JSON strings representing emergences
    public static Machine[] parts; // To store Machine objects directly as an array

    public static void main(String[] args) {
        // Sample JSON strings representing different "emergences"
        String[] emergences = {
                "{\"kind\": \"Humanoid\", \"bodyType\": \"physical\", \"faceType\": \"anthropomorphic\", \"reverie\": \"mechatypical\"}",
                "{\"kind\": \"Humanoid\", \"bodyType\": \"physical\", \"faceType\": \"anthropomorphic\", \"reverie\": \"biotypical\"}",
                "{\"kind\": \"Robot\", \"material\": \"metal\", \"function\": \"industrial\"}",
                "{\"kind\": \"Humanoid\", \"bodyType\": \"physical\", \"faceType\": \"anthropomorphic\"}"
        };

        // Parse the emergences and set them to the SystemWhole state
        emergencesFromPhenomena(emergences);
        // // Analyze the shapes based on the set emergences
        // reifyFrameOfReference();
        // System.out.println("Prelude of the Rise of the Machines: " + Arrays.deepToString(parts));
        // parts[0].emergeFromLimitations();
        // // Track humanoid machines and identify singularities
        // System.out.println(identitySingularityMachines());
        // Machine[] singularities = trackSingularityMachines();
        // System.out.println("Singularities: " + Arrays.deepToString(singularities));
    }

    public static void emergencesFromPhenomena(String[] emergences) {
        SystemWhole.emergences = emergences; 
    }

    public static void reifyFrameOfReference() {
        SystemWhole.parts = new Machine[emergences.length];
        int i = 0;
        for (String emergence:emergences)   {
            SystemWhole.parts[i++] = ShapeAnalyzer.analyze(emergence);
        }
    }

    public static boolean isHumanoid(Object[] machineProperties) {
        //Declare boolean values for properties
        boolean hasPhysicalBody = false;
        boolean hasAnthroFace = false;
        boolean hasBioRev = false;
    
        for (Object property:machineProperties) {
            PartState partState = (PartState) property;
            String partStateString = partState.toString();
            //Checks if property contains physical for bodyType
            if (partStateString.contains("physical")) {
                hasPhysicalBody = true;
            }
            //Checks if property contains anthropomorphic for faceType
            else if (partStateString.contains("anthropomorphic")) {
                hasAnthroFace = true;
            } 
            //Checks if property contains biotypical for reverie
            else if (partStateString.contains("biotypical")) {
                hasBioRev = true;
            }
        }
        //Return true if all conditions are true
        return (hasPhysicalBody && hasAnthroFace && hasBioRev);
    }

    // SystemWhole's logic to determine if a Machine is humanoid and count them
    public static int identitySingularityMachines() {
        //Counters for both humanoid and singularities
        int humanoidCount = 0;
        int singularityCount = 0;

        for (Machine machine:parts)    {
            //If the machine is humanoid, humanoidCount is incremented
            if (machine.isHumanoid())   {
                humanoidCount += 1;
            }
            //If the machine is NOT humanoid, singularityCount is incremented
            else    {
                singularityCount += 1;
            }
        }
        return humanoidCount + singularityCount;
    }

    public static Machine[] trackSingularityMachines() {
        //Create array to return
        Machine [] singularities = new Machine[identitySingularityMachines()];
        int i;
        //Each humanoid and singularities are put into the array
        for (i = 0; i < parts.length; ++i)  {
            singularities[i] = parts[i];
        }
        return singularities;
    }
}
