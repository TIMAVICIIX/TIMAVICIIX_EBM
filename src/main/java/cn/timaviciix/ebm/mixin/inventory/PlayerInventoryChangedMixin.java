package cn.timaviciix.ebm.mixin.inventory;

import cn.timaviciix.ebm.data.DataFactory;
import cn.timaviciix.ebm.data.book.BookData;
import cn.timaviciix.ebm.data.handler.BookNameHandler;
import cn.timaviciix.ebm.item.blockitems.BookBlockItem;
import cn.timaviciix.ebm.util.GlobalData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public class PlayerInventoryChangedMixin {

    @Shadow @Final public PlayerEntity player;

    //Change Book Custom Name By Mixin
    @Inject(method = "setStack", at = @At("TAIL"))
    private void onSetStack(int slot, ItemStack stack, CallbackInfo ci) {
        if (!stack.isEmpty() && stack.getItem() instanceof BookBlockItem) {
            PlayerInventory inventory = (PlayerInventory)(Object)this;
            PlayerEntity player = inventory.player;
            BookNameHandler.INSTANCE.renameItem(stack,player);
        }
    }

}
