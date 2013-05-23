@echo off
setlocal ENABLEDELAYEDEXPANSION


cd C:\Users\Logan\Documents\URA\train
set delta=4.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=train%%i.jpg
set myoutput=train_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! train%%A.jpg
			set myoutput=!myoutput! train_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i train_%delta%_output%d.jpg -vcodec h264 train_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\graveyard
set delta=4.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=graveyard%%i.jpg
set myoutput=graveyard_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! graveyard%%A.jpg
			set myoutput=!myoutput! graveyard_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i graveyard_%delta%_output%d.jpg -vcodec h264 graveyard_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\snowboarding
set delta=4.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=snowboarding%%i.jpg
set myoutput=snowboarding_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! snowboarding%%A.jpg
			set myoutput=!myoutput! snowboarding_%delta%_output%%A.jpg)
			
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i snowboarding_%delta%_output%d.jpg -vcodec h264 snowboarding_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\beach
set delta=4.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=beach%%i.jpg
set myoutput=beach_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! beach%%A.jpg
			set myoutput=!myoutput! beach_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i beach_%delta%_output%d.jpg -vcodec h264 beach_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\fishing
set delta=4.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=fishing%%i.jpg
set myoutput=fishing_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! fishing%%A.jpg
			set myoutput=!myoutput! fishing_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i fishing_%delta%_output%d.jpg -vcodec h264 fishing_%perFrames%_%delta%.mp4


cd C:\Users\Logan\Documents\URA\Residential
set delta=4.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=Residential%%i.jpg
set myoutput=Residential_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! Residential%%A.jpg
			set myoutput=!myoutput! Residential_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i Residential_%delta%_output%d.jpg -vcodec h264 Residential_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\sharks
set delta=4.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=sharks%%i.jpg
set myoutput=sharks_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! sharks%%A.jpg
			set myoutput=!myoutput! sharks_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i sharks_%delta%_output%d.jpg -vcodec h264 sharks_%perFrames%_%delta%.mp4


cd C:\Users\Logan\Documents\URA\truman
set delta=4.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=truman%%i.jpg
set myoutput=truman_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! truman%%A.jpg
			set myoutput=!myoutput! truman_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i truman_%delta%_output%d.jpg -vcodec h264 truman_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\UGAharlem
set delta=4.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=UGAharlem%%i.jpg
set myoutput=UGAharlem_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! UGAharlem%%A.jpg
			set myoutput=!myoutput! UGAharlem_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i UGAharlem_%delta%_output%d.jpg -vcodec h264 UGAharlem_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\snowmountain
set delta=2.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=snowmountain%%i.jpg
set myoutput=snowmountain_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! snowmountain%%A.jpg
			set myoutput=!myoutput! snowmountain_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i snowmountain_%delta%_output%d.jpg -vcodec h264 snowmountain_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\train
set delta=2.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=train%%i.jpg
set myoutput=train_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! train%%A.jpg
			set myoutput=!myoutput! train_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i train_%delta%_output%d.jpg -vcodec h264 train_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\graveyard
set delta=2.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=graveyard%%i.jpg
set myoutput=graveyard_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! graveyard%%A.jpg
			set myoutput=!myoutput! graveyard_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i graveyard_%delta%_output%d.jpg -vcodec h264 graveyard_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\snowboarding
set delta=2.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=snowboarding%%i.jpg
set myoutput=snowboarding_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! snowboarding%%A.jpg
			set myoutput=!myoutput! snowboarding_%delta%_output%%A.jpg)
			
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i snowboarding_%delta%_output%d.jpg -vcodec h264 snowboarding_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\beach
set delta=2.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=beach%%i.jpg
set myoutput=beach_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! beach%%A.jpg
			set myoutput=!myoutput! beach_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i beach_%delta%_output%d.jpg -vcodec h264 beach_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\fishing
set delta=2.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=fishing%%i.jpg
set myoutput=fishing_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! fishing%%A.jpg
			set myoutput=!myoutput! fishing_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i fishing_%delta%_output%d.jpg -vcodec h264 fishing_%perFrames%_%delta%.mp4


cd C:\Users\Logan\Documents\URA\Residential
set delta=2.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=Residential%%i.jpg
set myoutput=Residential_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! Residential%%A.jpg
			set myoutput=!myoutput! Residential_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i Residential_%delta%_output%d.jpg -vcodec h264 Residential_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\sharks
set delta=2.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=sharks%%i.jpg
set myoutput=sharks_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! sharks%%A.jpg
			set myoutput=!myoutput! sharks_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i sharks_%delta%_output%d.jpg -vcodec h264 sharks_%perFrames%_%delta%.mp4


cd C:\Users\Logan\Documents\URA\truman
set delta=2.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=truman%%i.jpg
set myoutput=truman_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! truman%%A.jpg
			set myoutput=!myoutput! truman_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i truman_%delta%_output%d.jpg -vcodec h264 truman_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\UGAharlem
set delta=2.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=UGAharlem%%i.jpg
set myoutput=UGAharlem_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! UGAharlem%%A.jpg
			set myoutput=!myoutput! UGAharlem_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i UGAharlem_%delta%_output%d.jpg -vcodec h264 UGAharlem_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\snowmountain
set delta=8.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=snowmountain%%i.jpg
set myoutput=snowmountain_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! snowmountain%%A.jpg
			set myoutput=!myoutput! snowmountain_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i snowmountain_%delta%_output%d.jpg -vcodec h264 snowmountain_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\train
set delta=8.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=train%%i.jpg
set myoutput=train_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! train%%A.jpg
			set myoutput=!myoutput! train_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i train_%delta%_output%d.jpg -vcodec h264 train_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\graveyard
set delta=8.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=graveyard%%i.jpg
set myoutput=graveyard_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! graveyard%%A.jpg
			set myoutput=!myoutput! graveyard_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i graveyard_%delta%_output%d.jpg -vcodec h264 graveyard_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\snowboarding
set delta=8.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=snowboarding%%i.jpg
set myoutput=snowboarding_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! snowboarding%%A.jpg
			set myoutput=!myoutput! snowboarding_%delta%_output%%A.jpg)
			
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i snowboarding_%delta%_output%d.jpg -vcodec h264 snowboarding_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\beach
set delta=8.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=beach%%i.jpg
set myoutput=beach_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! beach%%A.jpg
			set myoutput=!myoutput! beach_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i beach_%delta%_output%d.jpg -vcodec h264 beach_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\fishing
set delta=8.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=fishing%%i.jpg
set myoutput=fishing_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! fishing%%A.jpg
			set myoutput=!myoutput! fishing_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i fishing_%delta%_output%d.jpg -vcodec h264 fishing_%perFrames%_%delta%.mp4


cd C:\Users\Logan\Documents\URA\Residential
set delta=8.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=Residential%%i.jpg
set myoutput=Residential_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! Residential%%A.jpg
			set myoutput=!myoutput! Residential_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i Residential_%delta%_output%d.jpg -vcodec h264 Residential_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\sharks
set delta=8.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=sharks%%i.jpg
set myoutput=sharks_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! sharks%%A.jpg
			set myoutput=!myoutput! sharks_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i sharks_%delta%_output%d.jpg -vcodec h264 sharks_%perFrames%_%delta%.mp4


cd C:\Users\Logan\Documents\URA\truman
set delta=8.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=truman%%i.jpg
set myoutput=truman_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! truman%%A.jpg
			set myoutput=!myoutput! truman_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i truman_%delta%_output%d.jpg -vcodec h264 truman_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\UGAharlem
set delta=8.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=UGAharlem%%i.jpg
set myoutput=UGAharlem_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! UGAharlem%%A.jpg
			set myoutput=!myoutput! UGAharlem_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i UGAharlem_%delta%_output%d.jpg -vcodec h264 UGAharlem_%perFrames%_%delta%.mp4

cd C:\Users\Logan\Documents\URA\snowmountain
set delta=4.0
set perFrames=4
set totalFrames=250

for /L %%i in (1,%perFrames%,%totalFrames%) do (set /a end=%%i+%perFrames%-1
set /a beginning=%%i+1
set myinput=snowmountain%%i.jpg
set myoutput=snowmountain_%delta%_output%%i.jpg


	for /L %%A in (!beginning!,1,!end!) do (set myinput=!myinput! snowmountain%%A.jpg
			set myoutput=!myoutput! snowmountain_%delta%_output%%A.jpg)
		
	java -Xms2g -Xmx5g -cp C:\Users\Logan\Documents\video_contrast_enhancement\ Contrast !myinput! !myoutput! %delta%)
	
REM C:\Users\Logan\Downloads\ffmpeg-20130212-git-1ac0fa5-win64-static\bin\ffmpeg -i snowmountain_%delta%_output%d.jpg -vcodec h264 snowmountain_%perFrames%_%delta%.mp4





