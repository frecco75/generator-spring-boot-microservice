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
            default: 'auth-server'
        } , {
            type: 'string',
            name: 'parentProject',
            message: 'Enter the name of the project this belongs to',
            default: 'techocamp'
        },
        	{
        		type: 'string',
        		name: 'port',
        		message: 'Enter port number',
        		default: '9999'
        	}
        	,
        	{
        		type: 'string',
        		name: 'contextPath',
        		message: 'Enter the context path uri',
        		default: '/auth'
        	},
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
        }
        ,
        {
            type: 'checkbox',
            name: 'data',
            message: 'Select where users will be stored:',
            choices: [
                {
                    name: 'Jdbc',
                    value: 'jdbc',
                    checked: true
                }
                // , {
                //     name: 'In-Memory',
                //     value: 'mem'
                // }
            ]
        }, {
            type: 'checkbox',
            name: 'database',
            message: 'Select Database support:',
            choices: [
                // {
                //     name: 'H2',
                //     value: 'h2'
                // }, {
                //     name: 'HSQLDB',
                //     value: 'hsqldb'
                // }, {
                //     name: 'Apache Derby',
                //     value: 'derby'
                // }, 
                {
                    name: 'MySQL',
                    value: 'mysql',
                    checked: true
                }
                // , {
                //     name: 'PostgreSQL',
                //     value: 'postgresql'
                // }
            ]
        }
        ,
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
        }

    ];

    this.prompt(prompts, function(props) {
        props.bootVersion = '1.4.1.RELEASE';
        this.bootVersion = props.bootVersion;
        this.packageName = props.packageName;
        this.baseName = props.baseName;
        this.parentProject = props.parentProject;
        this.port = props.port;
        this.contextPath = props.contextPath;
        this.dockerTag = 'nexus.techolution.com:8123/'+props.baseName+'/'+props.parentProject;
        props.javaVersion = '1.8';
        this.javaVersion = props.javaVersion;
        props.packagingType = 'jar';
        this.packagingType = props.packagingType;


        this.data = props.data;
        this.database = props.database;
        this.ops = props.ops;
        this.buildTool = props.buildTool;

        // Packaging Type
        var hasPackagingType = function(packagingTypeStarter) {
            return props.packagingType.indexOf(packagingTypeStarter) !== -1;
        };
        this.jar = hasPackagingType('jar');
        this.war = hasPackagingType('war');

        
        // Spring Data
        var hasData = function(dataStarter) {
            return props.data.indexOf(dataStarter) !== -1;
        };
        this.jdbc = hasData('jdbc');
        this.mem = hasData('mem');
  
        // Databases
        var hasDatabase = function(databaseStarter) {
            return props.database.indexOf(databaseStarter) !== -1;
        };
        this.h2 = hasDatabase('h2');
        this.hsqldb = hasDatabase('hsqldb');
        this.derby = hasDatabase('derby');
        this.mysql = hasDatabase('mysql');
        this.postgresql = hasDatabase('postgresql');

     
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
    var resourceDir = rootDir+'src/main/resources';
    mkdirp(srcDir);

    if ('gradle' === this.buildTool[0]) {
        this.template('build.gradle', rootDir+'build.gradle');
    }
    if ('maven' === this.buildTool[0]) {
        this.template('pom.xml', rootDir+'pom.xml');
    }

    this.template('AuthServerApplication.java', srcDir + '/AuthServerApplication.java');

    this.template('auth/domain/Authority.java', srcDir + '/auth/domain/Authority.java');
    this.template('auth/domain/OAuthClientDetails.java', srcDir + '/auth/domain/OAuthClientDetails.java');
    this.template('auth/domain/OAuthClientDetailsBuilder.java', srcDir + '/auth/domain/OAuthClientDetailsBuilder.java');    
    this.template('auth/domain/Role.java', srcDir + '/auth/domain/Role.java');
    this.template('auth/domain/User.java', srcDir + '/auth/domain/User.java');
    this.template('auth/domain/UserBuilder.java', srcDir + '/auth/domain/UserBuilder.java');
    this.template('auth/domain/UserType.java', srcDir + '/auth/domain/UserType.java');

    this.template('auth/exception/OAuthclientValidationException.java', srcDir + '/auth/exception/OAuthclientValidationException.java');
    this.template('auth/exception/UserErrorMessages.java', srcDir + '/auth/exception/UserErrorMessages.java');
    this.template('auth/exception/UserErrorType.java', srcDir + '/auth/exception/UserErrorType.java');
    this.template('auth/exception/UserException.java', srcDir + '/auth/exception/UserException.java');

    this.template('auth/repository/UserRespository.java', srcDir + '/auth/repository/UserRespository.java');

    this.template('auth/service/UserService.java', srcDir + '/auth/service/UserService.java');

    this.template('config/CorsFilter.java', srcDir + '/config/CorsFilter.java');
    this.template('config/OAuth2AuthorizationServerConfigInMemory.java', srcDir + '/config/OAuth2AuthorizationServerConfigInMemory.java');
    this.template('config/WebSecurityConfig.java', srcDir + '/config/WebSecurityConfig.java');

    this.template('controller/RevokeTokenEndpoint.java', srcDir + '/controller/RevokeTokenEndpoint.java');
    this.template('controller/TokenController.java', srcDir + '/controller/TokenController.java');




 
        mkdirp('src/main/resources');
        mkdirp('src/main/resources/static');
        mkdirp('src/main/resources/templates');
        this.template('resources/application.yml', resourceDir + '/application.yml');
        this.template('resources/application-container.yml', resourceDir + '/application-container.yml');
        this.template('resources/application-local.yml', resourceDir + '/application-local.yml');
        this.template('resources/application-release.yml', resourceDir + '/application-release.yml');
 
    this.config.set('packageName', this.packageName);
    this.config.set('packageFolder', packageFolder);
};

SpringGenerator.prototype.projectfiles = function projectfiles() {};
