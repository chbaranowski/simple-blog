import org.gradle.api.*

class VersionClass implements Plugin<Project> {

    void apply(Project project) {
        project.getPlugins().apply(plugins.JavaPlugin)
        project.extensions.add("versionClass", VersionClassConfiguration)
        def javaGenSrc = new File(project.buildDir, "generated-sources")
        def makeVersionClassTask = project.task("makeVersionClass") << {
            def versionInfoPackagePath = project.versionClass.packageName.replace('.', '/')
            def versionClassFile = new File(javaGenSrc, "${versionInfoPackagePath}/VersionInfo.java")
            versionClassFile.parentFile.mkdirs();
            versionClassFile.withWriter { content ->
            content.write(
/** Start VersionInfo Template */
"""\
package ${project.versionClass.packageName};
 
/**
* Version info class provides some build meta informations.
*/
public class VersionInfo
{
   public static final String VERSION = "${project.version}";
   public static final String NAME = "${project.name}";
   public static final String GROUP = "${project.group}";
   public static final String BUILD_DATE = "${new Date()}";
}
 
""")
/** End VersionInfo Template */
            }
        }
        // Inputs for the task
        makeVersionClassTask.inputs.property("project.version", project.version)
        makeVersionClassTask.inputs.property("project.name",    project.name)
        makeVersionClassTask.inputs.property("project.group",   project.group)
        // Outputs of the task
        makeVersionClassTask.outputs.dir javaGenSrc
        // Add the javaGenSrc folder as source set
        project.sourceSets {
            main {
                java { srcDir "${javaGenSrc}" }
            }
        }
        // Update the task dependencies
        project.tasks.compileJava.dependsOn("makeVersionClass")
        project.tasks.eclipseClasspath.dependsOn("makeVersionClass")
    }

}

class VersionClassConfiguration {
    def String packageName = "project.version"
}