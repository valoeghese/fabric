/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.api.loot.v1;

import java.util.List;

import net.minecraft.world.loot.LootPool;
import net.minecraft.world.loot.LootTableRange;
import net.minecraft.world.loot.condition.LootCondition;
import net.minecraft.world.loot.entry.LootEntry;
import net.minecraft.world.loot.function.LootFunction;

/**
 * An interface implemented by all {@code net.minecraft.world.loot.LootPool} instances when
 * Fabric API is present. Contains accessors for various fields.
 */
public interface FabricLootPool {
	default LootPool asVanilla() {
		return (LootPool) this;
	}

	List<LootEntry> getEntries();
	List<LootCondition> getConditions();
	List<LootFunction> getFunctions();
	LootTableRange getRollsRange();
}
