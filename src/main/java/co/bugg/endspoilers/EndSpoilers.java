package co.bugg.endspoilers;

import net.minecraft.client.Minecraft;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Pattern;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class EndSpoilers {

    public static final Pattern reg = Pattern.compile("hulk|iron ?man|widow|gamora|thor|thanos|captain ?america|" +
            "time ?travel|end[ \\-]?game|avenge|peter parker|spider[ \\-]?man|loki|clint|jeremy renner|RDJ|" +
            "robert (?:downey|jr)|(?:doctor|dr)\\.? ?strange|benedict|cumberbatch|ant[ \\-]?man|paul rudd|pepper|potts|" +
            "mr?s\\.? ? pott?s?|gwenyth paltrow|star[ \\-]?lord|chris pratt|scarlett?|johans?son|josh brolin|" +
            "hemsworth|brie larson|carol denvers|dies in|infinity|gauntlet|tom holland|marvel|tom hiddleston|" +
            "nebula|karen gillan|war ?machine|don cheadle|bucky|barnes|sebastian stan|ancient one|tilda swinton|" +
            "sam wilson|falcon|okokye|danai gurira|wasp|rocket|raccoon|pym|stones|quill|hawkeye|snap|natasha|" +
            "hammer|(?:5|five) years|back in time|nick fury|samuel ?(l?\\.?)? ?jackson|chop.*head|quantum|stark|" +
            "steve rogers|bruce banner|asgard|romanoff|rhodey|wakanda|soul[ \\-]?stone|time[ \\-]?stone|reality|" +
            "mind[ \\-]?stone|space[ \\-]?stone|power[ \\-]?stone|stephen strange|steven strange|chadwick|boseman|panther|" +
            "hope van dyne|evangeline|wanda|maximoff|howard|jarvis|peggy carter|(?:turn|become)s? old|past|valkyrie|korg|" +
            "jane foster|mjolnir|morag|red skull|vormir|america'?s (?:as|but)|funeral|guardian'?s (?:of|the)? galaxy|" +
            "gotg|love you 3000?", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(reg.matcher(event.message.getUnformattedText()).find()) {
            event.setCanceled(true);

            ChatStyle style = new ChatStyle().setColor(EnumChatFormatting.RED).setChatHoverEvent(
                    new HoverEvent(HoverEvent.Action.SHOW_TEXT, event.message));
            IChatComponent newMessage = new ChatComponentText("Potential Avengers: Endgame spoiler blocked! Hover over " +
                    "this message to view the blocked message.").setChatStyle(style);

            Minecraft.getMinecraft().thePlayer.addChatMessage(newMessage);
        }
    }
}
