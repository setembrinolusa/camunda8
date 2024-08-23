package poc.com.camunda.archtests;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.junit.runner.RunWith;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "poc.com.camunda")
public class NamingConventionTests {

	@ArchTest
	static ArchRule controllers_should_be_suffixed = 
		classes()
			.that()
			.areNotAnonymousClasses()
			.and()
			.resideInAPackage("..adapters.http.rest.v1.controllers..")
			.should()
			.haveSimpleNameEndingWith("Controller");
	
	@ArchTest
	static ArchRule entities_should_be_suffixed = 
		classes()
			.that()
			.resideInAPackage("..core.domain.entities")
			.should()
			.haveSimpleNameEndingWith("Entity")
			.orShould()
			.haveSimpleNameEndingWith("EntityBuilder");
	
	@ArchTest
	static ArchRule usecases_should_be_suffixed = 
		classes()
			.that()
			.resideInAPackage("..core.usecases")
			.should()
			.haveSimpleNameEndingWith("UseCase")
			.orShould()
			.haveSimpleNameEndingWith("UseCaseImpl");	
	@ArchTest
	
	static ArchRule mappers_should_be_suffixed = 
		classes()
			.that()
			.resideInAPackage("..mappers")
			.should()
			.haveSimpleNameEndingWith("Mapper")
			.orShould()
			.haveSimpleNameEndingWith("MapperImpl");
	
	@ArchTest
	static ArchRule ports_should_be_suffixed = 
		classes()
			.that()
			.resideInAPackage("..core.domain.ports")
			.should()
			.haveSimpleNameEndingWith("Port");
	
	@ArchTest
	static ArchRule dataProviders_should_be_suffixed = 
		classes()
			.that()
			.resideInAPackage("..adapters.gateways")
			.should()
			.haveSimpleNameEndingWith("Gateway");

}
