package result_modeler;

import java.util.Comparator;

public class CustomComparator implements Comparator<CustomEntry> {
    @Override
    public int compare(CustomEntry o1, CustomEntry o2) {
        if (o1.getCount() < o2.getCount() || o1.getCount() == o2.getCount() && o1.getId() < o2.getId())
            return 1;
        return -1;
    }
}
