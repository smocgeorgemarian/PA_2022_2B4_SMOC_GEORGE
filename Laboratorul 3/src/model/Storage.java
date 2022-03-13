package model;

import java.security.InvalidParameterException;

public interface Storage {
    int getStorageCapacity();
    // compute the storage in specified unit
    default int getStorageCapacity(String unitOfStorage) {
        if (unitOfStorage.equals("megabyte"))
            return getStorageCapacity() * 1024;
        if (unitOfStorage.equals("kilobyte"))
            return getStorageCapacity() * 1024 * 1024;
        if (unitOfStorage.equals("byte"))
            return getStorageCapacity() * 1024 * 1024 * 1024;
        throw new InvalidParameterException();
    }
}
