#run using 'py thing-explainer-splitter.py "$(cat thing-explainer-words.txt)" > thing-explainer-words-list.txt'
import sys

wordlist = sys.argv[1]
for w in wordlist.split("|"):
    print(w)
