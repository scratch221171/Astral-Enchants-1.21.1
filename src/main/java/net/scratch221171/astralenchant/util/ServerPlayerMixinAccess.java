package net.scratch221171.astralenchant.util;

import java.util.List;

public interface ServerPlayerMixinAccess {
    List<ProtectedItemState> astralenchant$getProtectedItems();
    void astralenchant$addProtectedItem(ProtectedItemState state);
}
