var gulp = require('gulp');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var gutil = require('gulp-util');
var babelify = require('babelify');
var connect = require('gulp-connect');
var pump = require('pump');
var pug = require('gulp-pug');
var htmlmin = require('gulp-htmlmin');
var uglify = require('gulp-uglify');
var sass = require('gulp-sass');
var rename = require('gulp-rename');

var dependencies = ['react', 'react-dom','axios'];

gulp.task('serverDev', function() {
    connect.server({
        root: ['./dist/'],
        port: 3001,
        base: 'http://localhost',
        livereload: true,
        debug: true
    });
});

gulp.task('serverPro', function() {
    connect.server({
        root: ['./web/'],
        port: 3001,
        base: 'http://localhost',
        livereload: true,
        debug: true
    });
});

gulp.task('sass', function () {
  return gulp.src('./sass/*.scss')
    .pipe(sass().on('error', sass.logError))
    .pipe(gulp.dest('./dist/css/'));
});

gulp.task('sassMinify', function () {
    return gulp.src('./sass/*.scss')
        .pipe(sass({
            outputStyle: 'compressed'
        }))
        .pipe(rename({suffix: '.min'}))
        .pipe(gulp.dest('./web/css/'));
});

gulp.task('views', function buildHTML() {
  return gulp.src('./views/*.pug')
  .pipe(pug({
      doctype: 'html',
      pretty: true
  }))
  .pipe(gulp.dest('./dist/'))
});

gulp.task('compress', function (cb) {
  pump([
        gulp.src('./dist/js/*.js'),
        uglify(),
        rename({suffix: '.min'}),
        gulp.dest('./web/js/')
    ],
    cb
  );
});

gulp.task('minify', function() {
  return gulp.src('./dist/*.html')
    .pipe(htmlmin({collapseWhitespace: true}))
    .pipe(gulp.dest('./web/'));
});

gulp.task('scripts', function () {
    bundleApp(false);
});

gulp.task('deploy', function (){
	bundleApp(true);
});

gulp.task('watchDev', function () {
	gulp.watch(['./app/*.js'], ['scripts']);
    gulp.watch(['./views/*.pug'], ['views']);
    gulp.watch(['./sass/*.scss'], ['sass']);
});

gulp.task('watchAll', function () {
	gulp.watch(['./app/*.js'], ['scripts']);
    gulp.watch(['./views/*.pug'], ['views']);
    gulp.watch(['./sass/*.scss'], ['sass']);
    gulp.watch(['./dist/*.html'], ['minify']);
    gulp.watch(['./dist/js/*.js'], ['compress']);
    gulp.watch(['./sass/*.scss'], ['sassMinify']);
});

gulp.task('default', ['scripts','sass','views','minify','compress','sassMinify','watchAll','serverPro']);
gulp.task('servPro', ['watchAll','serverPro']);
gulp.task('servDev', ['watchDev','serverDev']);

var scriptsCount = 0;

function bundleApp(isProduction) {
    scriptsCount++;
    var appBundler = browserify({
        entries: './app/app.js',
        debug: true
    })

    if (!isProduction && scriptsCount === 1) {
        browserify({
            require: dependencies,
            debug: true
        })
        .bundle()
        .on('error', gutil.log)
        .pipe(source('vendors.js'))
        .pipe(gulp.dest('./dist/js/'));
    }
    if (!isProduction) {
        dependencies.forEach(function(dep) {
            appBundler.external(dep);
        })
    }

    appBundler
    .transform("babelify", {presets: ["env", "react"]})
    .bundle()
    .on('error', gutil.log)
    .pipe(source('bundle.js'))
    .pipe(gulp.dest('./dist/js/'));
}
