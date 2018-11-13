(ns hello-nrepl-middleware.core-test
  (:require [clojure.test :as t]
            [hello-nrepl-middleware.core :as sut]
            [nrepl.core :as nrepl]
            [nrepl.server :as server]))

(def ^:dynamic *server* nil)

(defn repl-server-fixture
  [f]
  (with-open [server (server/start-server :handler (server/default-handler #'sut/wrap-hello))]
    (binding [*server* server]
      (f))))

(t/use-fixtures :each repl-server-fixture)

(defn- ensure-map [x]
  (cond
    (sequential? x) (apply merge x)
    :else x))

(t/deftest wrap-hello-test
  (with-open [transport (nrepl/connect :port (:port *server*))]
    (let [client (nrepl/client transport Long/MAX_VALUE)
          id (str (java.util.UUID/randomUUID))
          response (ensure-map (nrepl/message client {:op "hello" :id id}))]
      (t/is (= (:status response) ["done"]))
      (t/is (= "world" (:hello response)))
      (t/is (= id (:id response))))))
