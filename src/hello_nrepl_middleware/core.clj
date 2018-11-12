(ns hello-nrepl-middleware.core)

(when-not (resolve 'set-descriptor!)
  (if (find-ns 'clojure.tools.nrepl)
    (require
     '[clojure.tools.nrepl.middleware :refer [set-descriptor!]]
     '[clojure.tools.nrepl.misc :refer [response-for]]
     '[clojure.tools.nrepl.transport :as transport])
    (require
     '[nrepl.middleware :refer [set-descriptor!]]
     '[nrepl.misc :refer [response-for]]
     '[nrepl.transport :as transport])))

(defn wrap-hello [handler]
  (fn [{:keys [op transport] :as msg}]
    (if (= op "hello")
      (transport/send transport (response-for msg {:status :done :hello "world"}))
      (handler msg))))

(when (resolve 'set-descriptor!)
  (set-descriptor!
   #'wrap-hello
   {:doc "Sample nREPL wrapper"
    :handles {"hello" {:doc "Sample nREPL middleware"
                       :requires {}
                       :returns {"hello" "world"
                                 "status" "done"}}}}))
