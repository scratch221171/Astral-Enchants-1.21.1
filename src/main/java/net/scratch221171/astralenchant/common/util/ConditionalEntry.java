package net.scratch221171.astralenchant.common.util;

import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.List;

public record ConditionalEntry<T>(
        T value,
        List<ICondition> conditions
) {}