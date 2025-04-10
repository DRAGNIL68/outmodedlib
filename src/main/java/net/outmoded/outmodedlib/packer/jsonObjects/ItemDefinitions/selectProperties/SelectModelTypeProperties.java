package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties;

import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.BlockState.BlockState;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.DisplayContext.DisplayContext;

public class SelectModelTypeProperties<T> {
    public static final SelectModelTypeProperties<BlockState> BLOCK_STATE = new SelectModelTypeProperties<>();
    public static final SelectModelTypeProperties<BlockState> CHARGE_TYPE = new SelectModelTypeProperties<>();
    public static final SelectModelTypeProperties<BlockState> CONTEXT_DIMENSION = new SelectModelTypeProperties<>();
    public static final SelectModelTypeProperties<BlockState> CONTEXT_ENTITY_TYPE = new SelectModelTypeProperties<>();
    public static final SelectModelTypeProperties<BlockState> CUSTOM_MODEL_DATA = new SelectModelTypeProperties<>();
    public static final SelectModelTypeProperties<DisplayContext> DISPLAY_CONTEXT = new SelectModelTypeProperties<>();
    public static final SelectModelTypeProperties<BlockState> LOCAL_TIME = new SelectModelTypeProperties<>();
    public static final SelectModelTypeProperties<BlockState> MAIN_HAND = new SelectModelTypeProperties<>();
    public static final SelectModelTypeProperties<BlockState> TRIM_MATERIAL = new SelectModelTypeProperties<>();


    private SelectModelTypeProperties() {};
}