(defproject kafka "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [spootnik/kinsky "0.1.16"]
          ;;       [ch.qos.logback/logback-classic "1.2.2"]
                 [org.slf4j/slf4j-nop "1.7.25"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler kafka.handler/app}
  :main kafka.demo
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
