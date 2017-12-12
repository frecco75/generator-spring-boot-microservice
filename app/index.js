'use strict';
var util = require('util');
var path = require('path');
var yeoman = require('yeoman-generator');
var chalk = require('chalk');
var mkdirp = require('mkdirp');

var SpringGenerator = module.exports = function SpringGenerator(args, options, config) {
    yeoman.generators.Base.apply(this, arguments);
};

util.inherits(SpringGenerator, yeoman.generators.Base);

SpringGenerator.prototype.askFor = function askFor() {
    var cb = this.async();

    console.log(chalk.green('\n.............DD88888888888888888,............\n' +
        '...........:888888888888888888888,...........\n' +
        '..........+88888888888888888888888+..........\n' +
        '.........,8888888888888888888888888..........\n' +
        '.........888888888888...888888888888.........\n' +
        '.......,88888887..D88...88Z..88888888,.......\n' +
        '.......8888888,...888...88D...=8888888.......\n' +
        '......D888888,..$8888...88887...8888888......\n' +
        '.....Z888888$..I88888...88888:..88888888,....\n' +
        '....D8888888...888888...88888D..,88888888....\n' +
        '....88888888,..888888..,888888...88888888....\n' +
        '....88888888,..8888888$888888D..,88888888....\n' +
        '....88888888I..Z8888888888888+..888888888....\n' +
        '.....Z8888888...O888888888888..,88888888.....\n' +
        '......88888888...,88888888D...,88888888......\n' +
        '.......88888888=.....?I+.....I88888888.......\n' +
        '.......,88888888D7.........ZD88888888,.......\n' +
        '.........888888888888888888888888888.........\n' +
        '.........,8888888888888888888888888..........\n' +
        '..........+88888888888888888888888+..........\n' +
        '...........,888888888888888888888:...........\n' +
        '.............DD888888888888888DD.............\n' +
        chalk.yellow('\nWelcome to the Spring Boot Generator\n\nLets get started!\n\n')));


    var prompts = [
        {
            type: 'string',
            name: 'packageName',
            message: 'Enter default package name:',
            default: 'com.techolution'
        }, {
            type: 'string',
            name: 'baseName',
            message: 'Enter microservice name of app:',
            default: 'training-server'
        } , {
            type: 'string',
            name: 'parentProject',
            message: 'Enter the name of the project this belongs to',
            default: 'techocamp'
        },
        {
            type: 'string',
            name: 'modelName',
            message: 'Enter the name of the first domain you plan on creating (should be lower case)',
            default: 'training'
        },
        {
        	type: 'string',
        	name:'portNumber',
        	message:'Enter port number',
        	default:'8080'
        },
        
        	
//        {
//            type: 'string',
//            name: 'packagingType',
//            message: 'Package type:',
//            default: 'jar'
//            choices: [
//                {
//                    name: 'Jar',
//                    value: 'jar'
//                }
//                , {
//                    name: 'War',
//                    value: 'war'
//                }
//           ]
//        },
        {
            type: 'checkbox',
            name: 'buildTool',
            message: 'Select build tool:',
            choices: [
                {
                    name: 'Gradle',
                    value: 'gradle',
                    checked: true

                }
                // , {
                //     name: 'Maven',
                //     value: 'maven'
                // }
            ]
        }, {
            type: 'checkbox',
            name: 'coreWeb',
            message: 'Select Core/Web Dependencies:',
            choices: [
                {
                    name: 'Web',
                    value: 'web',
                    checked: true
                },
//                {
//                    name: 'Jetty (Tomcat will be uninstalled)',
//                    value: 'jetty'
//                },
                {
                    name: 'Security',
                    value: 'security',
                    checked: true
                }
                , {
                    name: 'AOP',
                    value: 'aop'
                }
                ,  {
                    name: 'Websocket',
                    value: 'websocket'
                },

               {
                   name: 'Swagger',
                   value: 'swagger',
                   checked: true
               }
//                {
//                    name: 'Rest Repositories',
//                    value: 'rest'
//                },
//                {
//                    name: 'Hypermedia (HATEOAS)',
//                    value: 'hateoas'
//                },
//                {
//                    name: 'Mobile',
//                    value: 'mobile'
//                },
//                {
//                    name: 'REST Docs',
//                    value: 'restdocs'
//                }
            ]
        },
//        {
//            type: 'checkbox',
//            name: 'templates',
//            message: 'Select Template Engine:',
//            choices: [
//                {
//                    name: 'Thymeleaf',
//                    value: 'thymeleaf'
//                }, {
//                    name: 'Groovy Templates',
//                    value: 'gtemplates'
//                }, {
//                    name: 'Mustache',
//                    value: 'mustache'
//                }
//            ]
//        },
        {
            type: 'checkbox',
            name: 'data',
            message: 'Select Data support:',
            choices: [
                {
                    name: 'Jdbc',
                    value: 'jdbc'
                }, {
                    name: 'JPA',
                    value: 'jpa'
                }, {
                    name: 'MongoDB',
                    value: 'mongodb'
                }, {
                    name: 'Redis',
                    value: 'redis'
                },  {
                    name: 'Solr',
                    value: 'solr'
                }, {
                    name: 'Elasticsearch',
                    value: 'elasticsearch'
                }
            ]
        }, {
            type: 'checkbox',
            name: 'database',
            message: 'Select Database support:',
            choices: [
                {
                    name: 'H2',
                    value: 'h2'
                }, {
                    name: 'HSQLDB',
                    value: 'hsqldb'
                }, {
                    name: 'Apache Derby',
                    value: 'derby'
                }, {
                    name: 'MySQL',
                    value: 'mysql'
                }, {
                    name: 'PostgreSQL',
                    value: 'postgresql'
                }
            ]
        }, {
            type: 'checkbox',
            name: 'cloud',
            message: 'Select Spring Cloud support:',
            choices: [
                
//                {
//                    name: 'Config Server',
//                    value: 'configServer'
//                },
                {
                    name: 'Eureka',
                    value: 'eureka',
                    checked: true
                }
//                , {
//                    name: 'Eureka Server',
//                    value: 'eurekaServer'
//                }
                ,
                {
                   name: 'Zuul',
                   value: 'zuul'
               },
//                 {
//                    name: 'Feign',
//                    value: 'feign'
//                },
                 {
                    name: 'Hystrix',
                    value: 'hystrix'
                },
//                 {
//                    name: 'Hystrix Dashboard',
//                    value: 'hystrixDashboard'
//                },
                 {
                    name: 'OAuth2',
                    value: 'oauth2',
                    checked: true
                }
                , {
                    name: 'Ribbon',
                    value: 'ribbon'
                },
//                 {
//                    name: 'Turbine',
//                    value: 'turbine'
//                },
//                 {
//                    name: 'Turbine AMQP',
//                    value: 'turbineAmqp'
//                },
               {
                    name: 'Cloud Connectors',
                    value: 'connectors'
                }, {
                    name: 'Cloud Bootstrap',
                    value: 'bootstrap'
                }, {
                    name: 'Config Client',
                    value: 'configClient'
                },
                 {
                    name: 'AWS',
                    value: 'aws'
                }, {
                    name: 'AWS JDBC',
                    value: 'awsJdbc'
                }, {
                    name: 'AWS Messaging',
                    value: 'awsMessaging'
                }, {
                    name: 'Cloud Bus AMQP',
                    value: 'cloudBus'
                }, {
                    name: 'Cloud Security',
                    value: 'cloudSecurity'
                }
            ]
        }, {
            type: 'checkbox',
            name: 'io',
            message: 'Select I/O support:',
            choices: [
//                {
//                    name: 'Batch',
//                    value: 'batch'
//                },
//                {
//                    name: 'Integration',
//                    value: 'integration'
//                },
                {
                    name: 'JMS (HornetQ)',
                    value: 'jms'
                },
                {
                    name: 'AMQP',
                    value: 'amqp'
                },
                 {
                    name: 'Mail',
                    value: 'mail'
                },
                 {
                    name: 'Kafka',
                    value: 'kafka'
                }
            ]
        },
//        {
//            type: 'checkbox',
//            name: 'social',
//            message: 'Select Social APIs:',
//            choices: [
//                {
//                    name: 'Facebook',
//                    value: 'facebook'
//                }, {
//                    name: 'LinkedIn',
//                    value: 'linkedin'
//                }, {
//                    name: 'Twitter',
//                    value: 'twitter'
//                }
//            ]
//        },
        {
            type: 'checkbox',
            name: 'ops',
            message: 'Select OPS tools:',
            choices: [
                {
                    name: 'Actuator',
                    value: 'actuator',
                    checked: true
                }, {
                    name: 'Remote Shell',
                    value: 'remoteshell'
                }
            ]
        },
//        {
//            type: 'confirm',
//            name: 'useSpock',
//            message: 'Use Spock?',
//            default: true
//        },
        {
            type: 'string',
            name: 'groovyVersion',
            message: 'Enter Groovy version:',
            default: '2.4.4'
        }
    ];

    this.prompt(prompts, function(props) {
        props.bootVersion = '1.4.1.RELEASE';
        this.bootVersion = props.bootVersion;
        this.packageName = props.packageName;
        this.baseName = props.baseName;
        this.parentProject = props.parentProject;
        this.dockerTag = 'nexus.techolution.com:8123/'+props.parentProject+'/'+props.baseName;
        props.javaVersion = '1.8';
        this.javaVersion = props.javaVersion;
        this.portNumber=props.portNumber;
        this.modelName = props.modelName;
        this.capModelName = props.modelName.replace(/\b[a-z]/g, function(letter) {
                                               return letter.toUpperCase();
                                           });
        props.packagingType = 'jar';
        this.packagingType = props.packagingType;
        this.coreWeb = props.coreWeb;
        this.templates = props.templates;
        this.data = props.data;
        this.database = props.database;
        this.cloud = props.cloud;
        this.io = props.io;
        this.social = props.social;
        this.ops = props.ops;
        this.useSpock = props.useSpock;
        this.groovyVersion = props.groovyVersion;
        this.buildTool = props.buildTool;

        // Packaging Type
        var hasPackagingType = function(packagingTypeStarter) {
            return props.packagingType.indexOf(packagingTypeStarter) !== -1;
        };
        this.jar = hasPackagingType('jar');
        this.war = hasPackagingType('war');

        // Core/Web
        var hasCoreWeb = function(coreWebStarter) {
            return props.coreWeb.indexOf(coreWebStarter) !== -1;
        };
        this.web = hasCoreWeb('web');
        this.jetty = hasCoreWeb('jetty');
        this.security = hasCoreWeb('security');
        this.aop = hasCoreWeb('aop');
        this.websocket = hasCoreWeb('websocket');
        this.jersey = hasCoreWeb('jersey');
        this.rest = hasCoreWeb('rest');
        this.hateoas = hasCoreWeb('hateoas');
        this.mobile = hasCoreWeb('mobile');
        this.restdocs = hasCoreWeb('restdocs');
        this.swagger = hasCoreWeb('swagger');

        // Template Engines
        props.templates = [];
        var hasTemplate = function(templateStarter) {
            return props.templates.indexOf(templateStarter) !== -1;
        };
        this.thymeleaf = hasTemplate('thymeleaf');
        this.gtemplates = hasTemplate('gtemplates');
        this.mustache = hasTemplate('mustache');

        // Spring Data
        var hasData = function(dataStarter) {
            return props.data.indexOf(dataStarter) !== -1;
        };
        this.jdbc = hasData('jdbc');
        this.jpa = hasData('jpa');
        this.mongodb = hasData('mongodb');
        this.redis = hasData('redis');
        this.gemfire = hasData('gemfire');
        this.solr = hasData('solr');
        this.elasticsearch = hasData('elasticsearch');

        // Databases
        var hasDatabase = function(databaseStarter) {
            return props.database.indexOf(databaseStarter) !== -1;
        };
        this.h2 = hasDatabase('h2');
        this.hsqldb = hasDatabase('hsqldb');
        this.derby = hasDatabase('derby');
        this.mysql = hasDatabase('mysql');
        this.postgresql = hasDatabase('postgresql');

        // Spring Cloud
        prompts.push({
            type: 'string',
            name: 'usesCloud',
            message: 'usesCloud',
            default: false
        });
        var hasCloud = function(cloudStarter) {
            return props.cloud.indexOf(cloudStarter) !== -1;
        };
        this.connectors = hasCloud('connectors');
        this.bootstrap = hasCloud('bootstrap');
        this.configClient = hasCloud('configClient');
        this.configServer = hasCloud('configServer');
        this.eureka = hasCloud('eureka');
        this.eurekaServer = hasCloud('eurekaServer');
        this.feign = hasCloud('feign');
        this.hystrix = hasCloud('hystrix');
        this.hystrixDashboard = hasCloud('hystrixDashboard');
        this.oauth2 = hasCloud('oauth2');
        this.ribbon = hasCloud('ribbon');
        this.turbine = hasCloud('turbine');
        this.turbineAmqp = hasCloud('turbineAmqp');
        this.zuul = hasCloud('zuul');
        this.aws = hasCloud('aws');
        this.awsJdbc = hasCloud('awsJdbc');
        this.awsMessaging = hasCloud('awsMessaging');
        this.cloudBus = hasCloud('cloudBus');
        this.cloudSecurity = hasCloud('cloudSecurity');
        this.usesCloud = props.cloud.length > 0;

        // I/O
        var hasIO = function(ioStarter) {
            return props.io.indexOf(ioStarter) !== -1;
        };
        this.batch = hasIO('batch');
        this.integration = hasIO('integration');
        this.jms = hasIO('jms');
        this.amqp = hasIO('amqp');
        this.mail = hasIO('mail');
        this.kafka = hasIO('kafka');


        // Social
        props.social = [];
        var hasSocial = function(socialStarter) {
            return props.social.indexOf(socialStarter) !== -1;
        };
        this.facebook = hasSocial('facebook');
        this.linkedin = hasSocial('linkedin');
        this.twitter = hasSocial('twitter');

        // OPS
        var hasOps = function(opsStarter) {
            return props.ops.indexOf(opsStarter) !== -1;
        };
        this.actuator = hasOps('actuator');
        this.remoteshell = hasOps('remoteshell');

        cb();
    }.bind(this));
};

SpringGenerator.prototype.app = function app() {
    var packageFolder = this.packageName.replace(/\./g, '/');
    var rootDir = this.baseName+'/';
    var srcDir = rootDir+'src/main/java/' + packageFolder;
    var testDir = rootDir+'src/test/java/' + packageFolder;
    var resourceDir = rootDir+'src/main/resources';
    mkdirp(srcDir);

    if ('gradle' === this.buildTool[0]) {
        this.template('build.gradle', rootDir+'build.gradle');
    }
    if ('maven' === this.buildTool[0]) {
        this.template('pom.xml', rootDir+'pom.xml');
    }

    this.template('Application.java', srcDir + '/'+this.capModelName+'Application.java');

    if (this.swagger)
    {
        this.template('config/SwaggerConfiguration.java', srcDir + '/config/SwaggerConfiguration.java');
    }

    this.template('config/Config.java', srcDir + '/config/Config.java');
    

    if (this.security) {

    this.template('config/MethodSecurityConfig.java', srcDir + '/config/MethodSecurityConfig.java');
    this.template('config/OAuth2ResourceServerConfig.java', srcDir + '/config/OAuth2ResourceServerConfig.java');
    this.template('config/ResourceServerWebConfig.java', srcDir + '/config/ResourceServerWebConfig.java');
    }

    if (!this.zuul)
    {
    this.template('RestController.java', srcDir + '/controller/'+this.capModelName+'RestController.java');
    this.template('Service.java', srcDir + '/service/'+this.capModelName+'Service.java');
    this.template('Model.java', srcDir + '/model/'+this.capModelName+'.java');
    this.template('ServiceTest.java', testDir + '/service/'+this.capModelName+'ServiceTest.java');

        if (this.web) {
           this.template('config/CorsFilter.java', srcDir + '/config/CorsFilter.java');
            }
    }
//    if (this.useSpock) {
//        var testDir = 'src/test/groovy/' + packageFolder;
//        mkdirp(testDir);
//    }

    mkdirp('src/main/resources');
    this.template('resources/application.yml', resourceDir + '/application.yml');
    this.template('resources/application-local.yml',resourceDir + '/application-local.yml')
    this.template('resources/application-test.yml',resourceDir + '/application-test.yml')
    this.template('resources/application-container.yml',resourceDir + '/application-container.yml')
    this.template('resources/application-prod.yml',resourceDir + '/application-prod.yml')

    if (this.web || this.thymeleaf || this.gtemplates || this.mustache) {
        
        mkdirp('src/main/resources/static');
        mkdirp('src/main/resources/templates');

    }
    this.config.set('packageName', this.packageName);
    this.config.set('packageFolder', packageFolder);
};

SpringGenerator.prototype.projectfiles = function projectfiles() {};
