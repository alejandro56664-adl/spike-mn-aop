package co.com.spike.lambda.demo.my.function.powertools.architecture;
 
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

class LayeredArchitectureTest {

  @Test
  void layerDependenciesAreRespected() {
    JavaClasses importedClasses = new ClassFileImporter().importPackages("co.com.spike.lambda.demo.my.function.powertools");

    ArchRule layer_dependencies_are_respected = layeredArchitecture()
        //TODO recuerde eliminar los comentarios o agregarlos cuando agregue componentes de backend.
        .layer("Controllers").definedBy("co.com.spike.lambda.demo.my.function.powertools.controller..")
        .layer("Services").definedBy("co.com.spike.lambda.demo.my.function.powertools.service..")
        //.layer("Backend").definedBy("co.com.spike.lambda.demo.my.function.powertools.backend..")

        .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
        .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers");
        //.whereLayer("Backend").mayOnlyBeAccessedByLayers("Services");

    layer_dependencies_are_respected.check(importedClasses);

  }
}