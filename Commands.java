package Main;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class Commands extends ListenerAdapter {
    public String prefix = "-";
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        
        Random random = new Random();
		int a = random.nextInt(255)+0;
		int b = random.nextInt(255)+0;
		int c = random.nextInt(255)+0;
		
		Color cor = new Color(a,b,c);
        
        if(args[0].equalsIgnoreCase(prefix + "help") || args[0].equalsIgnoreCase(prefix + "h")) {
        	EmbedBuilder e = new EmbedBuilder();
        	e.setTitle("COMANDOS DO MELHOR BOT DO MUNDO:");
        	e.setColor(cor);
        	e.setDescription("basta ler");
        	e.addField("-help (h)", "🤨🤨🤨🤨🤨🤨", false);
        	e.addField("-inhouse (inh)", "inhouse obvio", false);	
        	e.addField("-end (e)", "deleta os canais criados pelo inhouse", false);
        	e.addField("-join (j)", "só entro no canal, não faço nada ainda", false);
        	e.addField("-leave (l)", "só saio do canal, flw", false);
        	e.setFooter("krampos bot versao sigma 1.0");
        	event.getChannel().sendMessageEmbeds(e.build()).queue();
        }

         
        
        
        // -Inhouse 
        if(args[0].equalsIgnoreCase(prefix + "inhouse") || args[0].equalsIgnoreCase(prefix + "inh")) {
            EmbedBuilder e = new EmbedBuilder();
            e.setTitle("INHOUSE");
            e.setDescription("Clique no 👍 para entrar na fila.");
            event.getChannel().sendMessageEmbeds(e.build()).queue(m -> {m.addReaction("👍").queue();
                                                                        m.delete().queueAfter(10, TimeUnit.MINUTES);  
            });
            
           event.getMessage().delete().queueAfter(30, TimeUnit.SECONDS);   
            

            } // Fim comando Inhouse
        
        
        
        // -End
        if(args[0].equalsIgnoreCase(prefix + "end") || args[0].equalsIgnoreCase(prefix + "e")) { 
        	Guild g = event.getGuild();
        	try { 
        		VoiceChannel vc1 = g.getVoiceChannelsByName("VITORIA", true).get(0);
            	VoiceChannel vc2 = g.getVoiceChannelsByName("BAHIA",true).get(0);
            	vc1.delete().queue();
            	vc2.delete().queue();
        		
        	}
        	catch (Exception e) { 
        		event.getChannel().sendMessage("Não encontrei o canal que tenho que deletar 😭😭😭").queue();
				e.printStackTrace();
        	}
         			
        	
        } // Fim comando End 
        
        
        // -Join
        if(args[0].equalsIgnoreCase(prefix + "join") || args[0].equalsIgnoreCase(prefix + "j")) {
        	Guild g = event.getGuild();
        	VoiceChannel channel = g.getVoiceChannels().stream().filter(filter -> filter.getMembers().contains(event.getMember())).findFirst().orElse(null);
        	AudioManager manager = g.getAudioManager();
        	manager.openAudioConnection(channel);	
        
        } // Fim comando Join 
        
        
        // -Leave
        if(args[0].equalsIgnoreCase(prefix + "leave") || args[0].equalsIgnoreCase(prefix + "l")) {
        	Guild g = event.getGuild();
        	AudioManager manager = g.getAudioManager();
        	manager.closeAudioConnection();
        
        } // Fim comando Leave 
        
       
        
        // -Timeout
        if(args[0].equalsIgnoreCase(prefix + "timeout") || args[0].equalsIgnoreCase(prefix + "tm")) { 
        	try { 
        		Member member = event.getMessage().getMentionedMembers().get(0);   
            	int time = Integer.parseInt(args[2]);
            	member.timeoutFor(time, TimeUnit.SECONDS).queue();
            	event.getMessage().delete().queue();    // If the bot breaks it was probably this line (Added without testing)
        	}
        	catch (IndexOutOfBoundsException e) {
        		event.getAuthor().openPrivateChannel().flatMap(channel -> channel.sendMessage("nao é assim nao boy aprenda 🤣🤣🤣")).queue();
        		event.getMember().timeoutFor(30, TimeUnit.SECONDS).queue();
        		event.getMessage().delete().queue();
        		e.printStackTrace();
        	
        	}
        	catch (Exception e) { 
        		event.getChannel().sendMessage("consigo nao pae.");
        		event.getChannel().delete().queue();
        		e.printStackTrace();
        	}
        	
        	
        } // Fim comando Timeout


        }


    }

