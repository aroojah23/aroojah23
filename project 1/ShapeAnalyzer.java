public class ShapeAnalyzer {
    private static final Object EMPTY_PROPERTY = new Object();

    public static Machine analyze(String json) {
        //Key value pairs from json
        String[][] entries = parseEntries(json);
        //Gets properties from the entries
        Object[] properties = reifyProperties(entries);
        //Takes out 'kind' from entries
        Object kind = reifyKind(entries);
        //Stores if the Machine is humanoid or not
        boolean humanConstrained = SystemWhole.isHumanoid(properties);
        //Machine object created
        return new Machine(kind, properties, humanConstrained);
    }

    public static String[][] parseEntries(String flatJson) {
        //Takes out curly braces 
        String twoDJson = flatJson.replaceAll("\\{\\}", "");
        //Key and value pair with commma
        String[] pairs = twoDJson.split(",");
        //Makes 2d array so key value pairs may be stored
        String[][] entries = new String[pairs.length][2];
        int i;
        //Each pair becomes a key value pair
        for (i = 0; i < pairs.length; ++i)  {
            //Makes proper key value pair with colon
            String[] keyValue = pairs[i].split(":");
            //Takes out the spaces
            entries[i][0] = keyValue[0].trim();
            entries[i][1] = keyValue[1].trim();
        }
        return entries;
    }

    public static String reifyKind(String[][] entries) {
        //Extract "kind" from entries
        return entries[0][1];
    }

    public static Object[] reifyProperties(String[][] entries) {
    //Count properties w/o "kind")
    int count = 0;
    for (String[] entry:entries) {
        if (!"kind".equals(entry[0])) {
            count++;
        }
    }

    //Make array
    PartState[] partStates = new PartState[count];
    int index = 0;
    //PartState objects in array
    for (String[] entry:entries) {
        if (!"kind".equals(entry[0])) {
            Object value = inferObject(entry[1]);
            partStates[index] = new PartState(entry[0], value);
            index++;
        }
    }
        return partStates;
    }

    public static boolean isDigit(char c) {
        //.isDigit checks if a character is between 0-9
        return Character.isDigit(c);
    }

    public static boolean hasNonNumbers(String value) {
        //Keeps track of the specific characters to look for
        int digitCount = 0;
        int underscoreCount = 0;
        int dotCount = 0;
        //Iterating through string
        for (char c:value.toCharArray())  {
            if (isDigit(c))    {
                digitCount += 1;
            }
            else if (c == '_')  {
                underscoreCount += 1;
            }
            else if (c == '.')  {
                dotCount += 1;
            }
        }
        //Non-number found
        if (digitCount > 1 && underscoreCount >= 1 && dotCount == 1)    {
            return true;
        }
        //No non-numbers found
        return false;
    }

    public static Object inferObject(String value) {
        //Checks if string is null or empty
        if (value == null || value.length() == 0)  {
            return EMPTY_PROPERTY;
        }
        //Checks for double or integer
        if (hasNonNumbers(value))   {
            return EMPTY_PROPERTY;
        }
        return value;
    }
}
