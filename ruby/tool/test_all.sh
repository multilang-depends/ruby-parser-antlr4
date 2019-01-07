for FILE in `ls ./test/*.rb`
do
echo $FILE; ./tool/test.sh $FILE
done
