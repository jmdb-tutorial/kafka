(ns kafka.repl-demo
  (:require [kinsky.client      :as client]
            [kinsky.async       :as async]
            [clojure.core.async :as a :refer [go <!! <! >! go-loop >!! timeout close! chan alts!!]]))

;; This file is designed to be a set of statements you can run in the repl.
;; If you are using CIDER, do C-c C-n, C-c M-n to execute the namespace and switch to it in the repl.

;; Create a channel - like a communications channel to another thread / process
(def ch (chan 1))

;; Do some basic comms:

;; Put on the channel
(>!! ch "hello")
;; true

;; Take off the channel
(<!! ch)
;; "hello"

;; If you take off when theres nothing there, it blocks
(<!! ch)

;; If you CTRL+C its still running so when you do
(>!! ch "hello")

;; The previous take gets that value, so you have to do it again if you want it to work...
(>!! ch "hello")
(<!! ch)

;; Now we can do something more asynchronous:
(go (let [value (<! ch)] (println "got a value from channel: " value)))

(>!! ch "hello")

;; Notice if you do this again, you don't see the output - because the async call finished, but you can run it again...

(go (let [value (<! ch)] (println "got a value from channel: " value)))

;; Also notice that go returns a channel itself. This is how it communicates back whatever is the result of the block:

(def rc (go (do (println "hello") true)))

;; #'kafka.repl-demo/rc
;; kafka.repl-demo> hello

(<!! rc)
;;true

;; Of course that block could also be a function:

(defn say-hello []
  (println "hello")
  "result")

(def rc (go (say-hello)))
;; #'kafka.repl-demo/rc
;; kafka.repl-demo> hello

(<!! rc)
;; "result"

;; Notice that the repl hangs and you have to press <RETURN> in order to get the prompt back

;; Create a function that takes a value off the channel and prints it out:
(defn take-and-print [channel]
  (let [value (<!! channel)]
    (println "got a value from channel: " value)))

;; Try it out - first put a value on the channel:
(>!! ch "hello")

;; Then try our function
(take-and-print ch)

