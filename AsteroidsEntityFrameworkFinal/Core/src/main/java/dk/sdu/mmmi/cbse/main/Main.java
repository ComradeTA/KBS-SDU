package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);

		for (String beanName : ctx.getBeanDefinitionNames()) {
			System.out.println(beanName);
		}

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Asteroids");
		int width = 1000;
		int height = 800;
		config.setWindowSizeLimits(width,height,width,height);
		config.setWindowedMode(width,height);
		config.setResizable(false);

		new Lwjgl3Application(ctx.getBean(Game.class), config);
	}
	
}
