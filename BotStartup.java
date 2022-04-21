package Main;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class BotStartup {
    public static void main(String[] args) throws LoginException { 
        JDABuilder jda = JDABuilder.createDefault("token");
        jda.setActivity(Activity.playing("activity"));
        jda.setStatus(OnlineStatus.ONLINE);
        jda.addEventListeners(new Commands());
        jda.addEventListeners(new ReactionListener());    
        jda.setChunkingFilter(ChunkingFilter.ALL);
        jda.setMemberCachePolicy(MemberCachePolicy.ALL);
        jda.enableIntents(GatewayIntent.GUILD_MEMBERS);
        jda.enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS); 
        jda.build();

        
    }

}
