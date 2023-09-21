import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.21"
	kotlin("plugin.spring") version "1.8.21"
	kotlin("plugin.jpa") version "1.8.21"
}

group = "com.test"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	maven {
		url = uri("https://repo.osgeo.org/repository/release/")
	}
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	runtimeOnly ("com.h2database:h2")
	runtimeOnly ("org.postgresql:postgresql")
	//
	implementation("org.geotools:gt-shapefile:28.1")
	implementation("org.geotools:gt-swing:28.1")
	implementation("org.geotools:gt-epsg-hsql:28.1")
	implementation("org.geotools:gt-main:28.1")
	implementation("org.geotools.xsd:gt-xsd-gml3:28.1")
	implementation("org.geotools:gt-geojson:28.1")

	// swagger-ui
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.0")

	testImplementation ("io.mockk:mockk:1.9.3")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}