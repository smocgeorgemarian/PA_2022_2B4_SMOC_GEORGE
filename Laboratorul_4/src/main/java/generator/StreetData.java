package generator;

import java.util.Objects;

public class StreetData {
    private final int fNodeIndex;
    private final int sNodeIndex;

    public StreetData(int fNodeIndex, int sNodeIndex) {
        this.fNodeIndex = fNodeIndex;
        this.sNodeIndex = sNodeIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreetData that = (StreetData) o;
        return fNodeIndex == that.fNodeIndex && sNodeIndex == that.sNodeIndex;
    }

    @Override
    public String toString() {
        return "StreetData{" +
                "fNodeIndex=" + fNodeIndex +
                ", sNodeIndex=" + sNodeIndex +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(fNodeIndex, sNodeIndex);
    }

    public int getfNodeIndex() {
        return fNodeIndex;
    }

    public int getsNodeIndex() {
        return sNodeIndex;
    }
}
