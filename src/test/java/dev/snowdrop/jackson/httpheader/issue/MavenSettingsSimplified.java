package dev.snowdrop.jackson.httpheader.issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.jspecify.annotations.Nullable;

import java.util.*;

@JacksonXmlRootElement(
    localName = "settings"
)
public class MavenSettingsSimplified {
    public final @Nullable Servers servers;

    @JsonCreator
    public MavenSettingsSimplified(@JsonProperty("servers") @Nullable Servers servers) {
        this.servers = servers;
    }

    public @Nullable Servers getServers() {
        return this.servers;
    }

    public MavenSettingsSimplified withServers(final @Nullable Servers servers) {
        return this.servers == servers ? this : new MavenSettingsSimplified(servers);
    }

    public static class Servers {
        @JacksonXmlProperty(
            localName = "server"
        )
        @JacksonXmlElementWrapper(
            useWrapping = false
        )
        private List<Server> servers = Collections.emptyList();

        public Servers merge(@Nullable Servers servers) {
            Map<String, Server> merged = new LinkedHashMap();

            for(Server server : this.servers) {
                merged.put(server.id, server);
            }

            if (servers != null) {
                servers.getServers().forEach((serverx) -> merged.putIfAbsent(serverx.getId(), serverx));
            }

            return new Servers(new ArrayList(merged.values()));
        }

        public List<Server> getServers() {
            return this.servers;
        }

        public void setServers(final List<Server> servers) {
            this.servers = servers;
        }

        public Servers(final List<Server> servers) {
            this.servers = servers;
        }

        public Servers() {
        }

        public Servers withServers(final List<Server> servers) {
            return this.servers == servers ? this : new Servers(servers);
        }
    }

    public static class Server {
        private final String id;
        private final String username;
        private final String password;
        public final ServerConfiguration configuration;

        @JsonCreator
        public Server(@JsonProperty("id") String id, @JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("configuration") ServerConfiguration configuration) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.configuration = configuration;
        }

        public String getId() {
            return this.id;
        }

        public String getUsername() {
            return this.username;
        }

        public String getPassword() {
            return this.password;
        }

        public ServerConfiguration getConfiguration() {
            return this.configuration;
        }

        public Server withId(final String id) {
            return this.id == id ? this : new Server(id, this.username, this.password, this.configuration);
        }

        public Server withUsername(final String username) {
            return this.username == username ? this : new Server(this.id, username, this.password, this.configuration);
        }

        public Server withPassword(final String password) {
            return this.password == password ? this : new Server(this.id, this.username, password, this.configuration);
        }

        public Server withConfiguration(final ServerConfiguration configuration) {
            return this.configuration == configuration ? this : new Server(this.id, this.username, this.password, configuration);
        }
    }

    public static class ServerConfiguration {
        @JacksonXmlProperty(
            localName = "property"
        )
        @JacksonXmlElementWrapper(
            localName = "httpHeaders",
            useWrapping = true
        )
        @JsonIgnore
        private final @Nullable List<HttpHeader> httpHeaders;
        private final @Nullable Long timeout;

        @JsonCreator
        public ServerConfiguration(@JsonProperty("httpHeaders") @Nullable List<HttpHeader> httpHeaders, @JsonProperty("timeout") @Nullable Long timeout) {
            this.httpHeaders = httpHeaders;
            this.timeout = timeout;
        }

        public @Nullable List<HttpHeader> getHttpHeaders() {
            return this.httpHeaders;
        }

        public @Nullable Long getTimeout() {
            return this.timeout;
        }

        public ServerConfiguration withHttpHeaders(final @Nullable List<HttpHeader> httpHeaders) {
            return this.httpHeaders == httpHeaders ? this : new ServerConfiguration(httpHeaders, this.timeout);
        }

        public ServerConfiguration withTimeout(final @Nullable Long timeout) {
            return this.timeout == timeout ? this : new ServerConfiguration(this.httpHeaders, timeout);
        }
    }

    public static class HttpHeader {
        private final String name;
        private final String value;

        @JsonCreator
        public HttpHeader(@JsonProperty("name") String name, @JsonProperty("value") String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }

        public HttpHeader withName(final String name) {
            return this.name == name ? this : new HttpHeader(name, this.value);
        }

        public HttpHeader withValue(final String value) {
            return this.value == value ? this : new HttpHeader(this.name, value);
        }
    }
}
