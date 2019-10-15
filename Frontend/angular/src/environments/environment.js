function environment(extraEnvironment) {
    var env = _.extend(_.pick(process.env, 'build', 'app'), extraEnvironment);
    var build = utils.getValueOrDefault(infos.builds, env.build, 'dev');
    var app = utils.getValueOrDefault(infos.apps, env.app, 'default');
    var buildId = utils.findKeyForExactValue(infos.builds, build);
    var target = app.builds[buildId];

    return {
        production: false    };
}

module.exports = environment;
