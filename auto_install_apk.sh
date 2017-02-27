adb devices | while read line
do
    if [ ! "$line" = "" ] && [ `echo $line | awk '{print $2}'` = "device" ]
    then
        device=`echo $line | awk '{print $1}'`
        echo "Run >>adb -s $device $@"
        adb -s $device uninstall com.workshop.todo.todo
        adb -s $device $@
    fi
done

#sh test.sh install -r app/build/outputs/apk/app-debug.apk
