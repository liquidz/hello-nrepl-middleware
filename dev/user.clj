(ns user
  (:require [hello-nrepl-middleware.core :as core]
            [nrepl.server :as server]))

(defonce ^:private serv (atom nil))

(defn- build-handler []
  (server/default-handler #'core/wrap-hello))

(defn start []
  (when-not @serv
    (println "Starting server")
    (reset! serv (server/start-server :port 12345
                                      :handler (build-handler)))))

(defn stop []
  (when @serv
    (println "Stopping server")
    (server/stop-server @serv)
    (reset! serv nil)))

(defn go []
  (stop)
  (start))

(defn reset []
  (println "Resetting server")
  (require '[hello-nrepl-middleware.core :as core] :reload-all)
  (go))
