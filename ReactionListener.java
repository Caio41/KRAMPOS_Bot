package Main;

import java.awt.Color;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter {
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
    	
    	
    	
    	if(event.getUser().isBot()) return;
    	
    	event.retrieveMessage().queue(msg -> {
   
    	MessageReaction react = msg.getReactions().stream().filter(r -> r.getReactionEmote().getName().equals(event.getReactionEmote().getName())).findFirst().orElse(null);
    if(msg.getAuthor().equals(event.getJDA().getSelfUser())) {
    	
    
    	// react.getCount for 10 players == 11
    	// takeAsync(x) for 10 players == 10
    	  
    	if(react != null) {
    		if(react.getCount() == 11) {
    			
    		         List<User> users;
				try {
					users = react.retrieveUsers().takeAsync(10).get();  // Generating the List of Users
					Collections.shuffle(users);                        // Randomizing the order of Users 3x
					Collections.shuffle(users);
					Collections.shuffle(users);
					
					// Time A
					String p1 = users.get(0).getAsMention();                
					String p2 = users.get(1).getAsMention();
					String p3 = users.get(2).getAsMention();
					String p4 = users.get(3).getAsMention();
					String p5 = users.get(4).getAsMention();
					// Time B
					String p6 = users.get(5).getAsMention();
					String p7 = users.get(6).getAsMention();
					String p8 = users.get(7).getAsMention();
					String p9 = users.get(8).getAsMention();
					String p10 = users.get(9).getAsMention();
					
					Guild guild = event.getGuild();
					
					// Users to members
					Member m1 = guild.getMember(users.get(0));
					Member m2 = guild.getMember(users.get(1));
					Member m3 = guild.getMember(users.get(2));
					Member m4 = guild.getMember(users.get(3));
					Member m5 = guild.getMember(users.get(4));
					
					Member m6 = guild.getMember(users.get(5));
					Member m7 = guild.getMember(users.get(6));
					Member m8 = guild.getMember(users.get(7));
					Member m9 = guild.getMember(users.get(8));
					Member m10 = guild.getMember(users.get(9));
					
				
					
					Category category = guild.getCategories().stream().filter(filter -> filter.getMembers().contains(event.getMember())).findFirst().orElse(null);
					
					
					category.createVoiceChannel("VITORIA").queue(channel -> {
						
						String id = channel.getId();
						AudioChannel timeA = guild.getVoiceChannelById(id);
						
						guild.moveVoiceMember(m1, timeA).queue();
						guild.moveVoiceMember(m2, timeA).queue();
						guild.moveVoiceMember(m3, timeA).queue();
						guild.moveVoiceMember(m4, timeA).queue();
						guild.moveVoiceMember(m5, timeA).queue();
						
						
					});
					
					category.createVoiceChannel("BAHIA").queue(channel -> {
						
						String id = channel.getId();
						AudioChannel timeB = guild.getVoiceChannelById(id);
						
						guild.moveVoiceMember(m6, timeB).queue();
						guild.moveVoiceMember(m7, timeB).queue();
						guild.moveVoiceMember(m8, timeB).queue();
						guild.moveVoiceMember(m9, timeB).queue();
						guild.moveVoiceMember(m10, timeB).queue();     
					});
			
					
					Random random = new Random();
					int a = random.nextInt(255)+0;
					int b = random.nextInt(255)+0;
					int c = random.nextInt(255)+0;
					
					Color cor = new Color(a,b,c);
							
					EmbedBuilder eb = new EmbedBuilder();        //Embed
					eb.setTitle("Inhouse");
					eb.setColor(cor);
					eb.setThumbnail(event.getJDA().getSelfUser().getAvatarUrl());
					eb.addField("Time A", p1+", "+p2+", "+p3+", "+p4+", "+p5, false);
					eb.addField("Time 2", p6+", "+p7+", "+p8+", "+p9+", "+p10, false);
					event.getChannel().sendMessageEmbeds(eb.build()).queue();

	    			
				} //End of tryAction
				
				catch (Exception e) {
					event.getChannel().sendMessage("ERRO CHAMEM O MITO --> <@!185803605108981761> <--").queue();
					e.printStackTrace();
					

				} //End of catchAction
					
    		}
    	}
    	
    }	
    	});
       
        
    }

}



