# Astral Enchant
![Modrinth Downloads](https://img.shields.io/modrinth/dt/astralenchant?style=for-the-badge&logo=modrinth&label=Modrinth&color=%2300af5c)
![CurseForge Downloads](https://img.shields.io/curseforge/dt/1425211?style=for-the-badge&logo=curseforge&label=CurseForge&color=%23F56A00)

<font color="#ff0000">***Note: This mod is still in alpha.***  
*It is strongly recommended to back up your data before installing this mod.*</font>

Since my English is still not good enough, I have corrected the readme using LLM :)

# What does this mod do?
This mod adds several craftable enchantments.

There are many mods that add large numbers of low-quality enchantments: increased jump height, simple potion effects, and other minor stat boosts. Most of them are shallow and uninteresting.

Do you find such enchantments boring? Do you want more technical, interesting, and practical enchantments?

If so, this mod may be what you are looking for.

Some enchantments are extremely powerful and expensive. If you do not like them, they can be disabled via the config.

## Enchantments (Currently: 12)

All enchantments can be toggled individually via the config.

- **Mitigation Piercing** *– weapon*

  Attacks with this item penetrate various forms of damage reduction, such as armor, potion effects, and invulnerability.

  May not work on mobs with hard-coded damage logic from other mods.

- **Last Stand** *– armor*

  Consumes experience points to prevent death. The higher the total enchantment level across all equipped armor pieces, the fewer experience points are consumed. This enchantment cannot prevent death caused by void damage or the `/kill` command.

- **Item Protection (Curse)** *– any item*

  Prevents dropped items from despawning or being destroyed by fire, the void, or similar effects. Items with this enchantment cannot have their enchantments modified. Additionally, inventory slots containing such items cannot be forcibly replaced by other items. (Vanilla mechanics are not affected.)

- **Essence of Enchant** *– any item*

  Increases all item stats based on the total level of all other enchantments on the item, excluding this one.

- **Cooldown Reduction** *– chestplate*

  Reduces the cooldown of all items, such as shields and Ender Pearls.

- **Feather Touch** *– mining tools*

  Allows you to collect all destructible blocks. While crouching, the block’s internal data is stored in the item.

  *Warning: Nesting containers can create NBT bombs. Use caution when using this enchantment in multiplayer environments.*

- **Adventurer's Lore** *– boots*

  Increases luck and the amount of experience gained from blocks based on the number of completed advancements.

- **Compatibility** *– bundle*

  Enchantments applied to a bundle with this enchantment are applied to the items inside it, ignoring conflicts and other restrictions. Also prevents bundle repair costs from increasing.

- **Endless Appetite** *– chestplate*

  Enhances natural regeneration by removing its cooldown and directly restoring the excess food and hidden saturation beyond their normal caps. Additionally, you can eat at any time, regardless of your current saturation level.

- **Momentum** *– chestplate*

  Negates various movement speed reduction effects, such as item usage slowdown, spider webs, and soul sand.

- **Instant Teleport** *– helmet*

  When using an Ender Pearl, teleports you to the location you are looking at. While sneaking, you will teleport to the opposite side of the targeted block. If no valid target is found in the line of sight, the teleport is cancelled and the Ender Pearl is not consumed.

- **Overload** *– any item*

  Grants the item an Overload level, which increases the levels of existing enchantments by the Overload value. The current Overload level is displayed in the tooltip. The maximum Overload value is 2,147,483,647 rather than 255, allowing enchantments to effectively exceed level 255.

**[Enchantment Descriptions](https://modrinth.com/mod/enchantment-descriptions) is supported.**

**A recipe viewer mod (such as [EMI](https://modrinth.com/mod/emi)) is recommended.**