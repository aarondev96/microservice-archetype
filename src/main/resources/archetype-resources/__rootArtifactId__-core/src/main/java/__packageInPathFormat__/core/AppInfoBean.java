#set($pound='#')
#set($dollar='$')
#set($escape='\' )
package ${package}.core;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppInfoBean {

	@Value("${dollar}{spring.application.name:unknown}")
	private String name;

	@Value("${dollar}{server.port:8080}")
	private Integer port;

}
