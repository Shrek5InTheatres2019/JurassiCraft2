package org.jurassicraft.server.storagedisc;

import com.google.common.base.Supplier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StorageTypeRegistry
{
    private static Map<String, Supplier<? extends IStorageType>> storageTypes = new HashMap<String, Supplier<? extends IStorageType>>();

    public void init()
    {
        register("DinoDNA", new Supplier<IStorageType>()
        {
            @Override
            public DinosaurDNAStorageType get()
            {
                return new DinosaurDNAStorageType();
            }
        });
        register("PlantDNA", new Supplier<IStorageType>()
        {
            @Override
            public PlantDNAStorageType get()
            {
                return new PlantDNAStorageType();
            }
        });
    }

    private void register(String id, Supplier<? extends IStorageType> storageType)
    {
        storageTypes.put(id, Objects.requireNonNull(storageType));
    }

    public static IStorageType getStorageType(String id)
    {
        if (id == null || id.isEmpty())
        {
            id = "DinoDNA";
        }

        return storageTypes.get(id).get();
    }
}
