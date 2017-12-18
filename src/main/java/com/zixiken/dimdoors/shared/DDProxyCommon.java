package com.zixiken.dimdoors.shared;

import com.zixiken.dimdoors.DimDoors;
import com.zixiken.dimdoors.shared.blocks.ModBlocks;
import com.zixiken.dimdoors.shared.entities.EntityMonolith;
import com.zixiken.dimdoors.shared.items.ModItems;
import com.zixiken.dimdoors.shared.sound.ModSounds;
import com.zixiken.dimdoors.shared.tileentities.*;
import com.zixiken.dimdoors.shared.util.DefaultSchematicGenerator;
import com.zixiken.dimdoors.shared.world.DimDoorDimensions;
import com.zixiken.dimdoors.shared.world.ModBiomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class DDProxyCommon implements IDDProxy {

    @Override
    public void onPreInitialization(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(EventHandler.class);
        MinecraftForge.EVENT_BUS.register(ModBlocks.class);
        MinecraftForge.EVENT_BUS.register(ModItems.class);
        MinecraftForge.EVENT_BUS.register(CraftingManager.class); // TODO: ModRecipes?
        MinecraftForge.EVENT_BUS.register(ModSounds.class);
        MinecraftForge.EVENT_BUS.register(ModBiomes.class);

        DimDoorDimensions.registerDimensions();

        GameRegistry.registerTileEntity(TileEntityVerticalEntranceRift.class, "TileEntityVerticalEntranceRift"); // TODO: new forge registry system?
        GameRegistry.registerTileEntity(TileEntityFloatingRift.class, "TileEntityFloatingRift");
        GameRegistry.registerTileEntity(TileEntityHorizontalEntranceRift.class, "TileEntityHorizontalEntranceRift");

        EntityRegistry.registerModEntity(new ResourceLocation(DimDoors.MODID, "mob_monolith"), EntityMonolith.class, "monolith", 0, DimDoors.instance, 70, 1, true);
        EntityRegistry.registerEgg(new ResourceLocation(DimDoors.MODID, "mob_monolith"), 0, 0xffffff);
    }

    @Override
    public void onInitialization(FMLInitializationEvent event) {

        SchematicHandler.INSTANCE.loadSchematics();
        DefaultSchematicGenerator.generateDefaultSchematics();
    }
}
