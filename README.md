## Maven Settings with httpheader

The following Maven Settings XML

```xml
<settings>
    <servers>
        <server>
            <id>maven-snapshots</id>
            <configuration>
                <httpHeaders>
                    <property>
                        <name>X-JFrog-Art-Api</name>
                        <value>myApiToken</value>
                    </property>
                </httpHeaders>
            </configuration>
        </server>
    </servers>
</settings>
```

is generated as such a string during jackson serialization

```xml
<settings>
   <servers>
       <server>
           <id>maven-snapshots</id>
           <configuration>
               <httpHeaders>
                   <name>X-JFrog-Art-Api</name>
                   <value>myApiToken</value>
               </httpHeaders>
               <timeout>10000</timeout>
           </configuration>
       </server>
   </servers>
</settings>
```

The problem is certainly occurring with the Java class definition of `MavenSettingsSimplified` !

## Usefull links

https://stackoverflow.com/questions/76575998/jackson-xmlmapper-xml-serialization-of-java-map-with-wrapper-entry-element