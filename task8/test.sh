if [ -z $1 ]; then
    DIR=./tests/HW8

fi

ghc Main.hs

for FILE in $DIR/*; do
    cp $FILE input.txt
    ./Main
    echo -e "\tOutput for $FILE: "
    cat output.txt
    echo ""
done
