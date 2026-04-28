package by.timeslowly.duacan_delight.common.world;

import by.timeslowly.duacan_delight.registry.DDBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class VillageCropModifier {

    public static void onServerAboutToStart(final @NotNull ServerAboutToStartEvent event) {
        var processorLists = event.getServer().registryAccess()
                .registryOrThrow(Registries.PROCESSOR_LIST);

        // 创建替换规则：30% 几率将小麦替换成盖菜，20% 几率将土豆替换成盖菜
        StructureProcessor gaicaiProcessor = new RuleProcessor(List.of(
                new ProcessorRule(
                        new RandomBlockMatchTest(Blocks.WHEAT, 0.6F),
                        AlwaysTrueTest.INSTANCE,
                        DDBlocks.GAICAI_CROP.get().defaultBlockState()
                ),
                new ProcessorRule(
                        new RandomBlockMatchTest(Blocks.POTATOES, 0.3F),
                        AlwaysTrueTest.INSTANCE,
                        DDBlocks.GAICAI_CROP.get().defaultBlockState()
                )
        ));

        // 向所有生物群系的农田处理器列表注入规则
        addProcessorToList(ResourceLocation.parse("minecraft:farm_plains"), gaicaiProcessor, processorLists);
        addProcessorToList(ResourceLocation.parse("minecraft:farm_snowy"), gaicaiProcessor, processorLists);
        addProcessorToList(ResourceLocation.parse("minecraft:farm_savanna"), gaicaiProcessor, processorLists);
        addProcessorToList(ResourceLocation.parse("minecraft:farm_taiga"), gaicaiProcessor, processorLists);
        addProcessorToList(ResourceLocation.parse("minecraft:farm_desert"), gaicaiProcessor, processorLists);
    }

    private static void addProcessorToList(ResourceLocation targetListId,
                                           StructureProcessor processorToAdd,
                                           net.minecraft.core.@NotNull Registry<StructureProcessorList> registry) {
        registry.getOptional(targetListId).ifPresent(processorList -> {
            try {
                // 反射获取 "list" 字段
                java.lang.reflect.Field field = StructureProcessorList.class.getDeclaredField("list");
                field.setAccessible(true);
                @SuppressWarnings("unchecked")
                List<StructureProcessor> originalList = (List<StructureProcessor>) field.get(processorList);
                List<StructureProcessor> newList = new ArrayList<>(originalList);
                newList.add(processorToAdd);
                // 通过反射重新设置
                field.set(processorList, newList);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Failed to add processor to " + targetListId, e);
            }
        });
    }
}