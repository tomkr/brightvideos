(ns brightvideos.core
  (:require
    [reagent.core :as reagent :refer [atom cursor]]
    [devcards.core])
  (:require-macros [devcards.core :as dc :refer [defcard-rg]]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom
  { :videos {
    1 { :title "test"
        :url "http://www.youtube.com"
        :score 10 }
    2 { :title "test 2"
        :url "http://www.twitch.tv"
        :score 8 }}}))

; Upvote button
(defn upvote-button [score]
  [:a {:on-click #(swap! score inc)}
    [:i.fa.fa-plus]])

; Downvote button
(defn downvote-button [score]
  [:a {:on-click #(swap! score dec)}
    [:i.fa.fa-minus]])

; A single video item component
(defn video-item [video]
  [:li.video-item
    [:div.votes
      [upvote-button (cursor video [:score])]
      [downvote-button (cursor video [:score])]]
    [:div.description
      [:a {:href (:url @video) :target "_blank"} (:title @video)]]])

; A list of video items
(defn video-list [state]
  [:div
    [:h1 "Brightvideos"]
    [:ul
      (for [[index _] (:videos @state)]
        ^{:key index}[video-item (cursor state [:videos index])])]])

(defn main []
  (if-let [node (.getElementById js/document "app")]
    (reagent/render-component [video-list app-state]
      (. js/document (getElementById "app")))))

(main)

; Devcards
(defcard-rg video-list
  "## A video item list"
  [video-list app-state]
  app-state
  {:inspect-data true :history true })

(defonce single-video-state (atom
  { :title "test"
    :url "http://www.youtube.com"
    :score 10 }))

(defcard-rg video-item
  "## A single video item"
  [video-item single-video-state]
  single-video-state
  {:inspect-data true :history true })

(defonce upvote-score (atom 0))

(defcard-rg upvote-button
  "## A button for upvoting"
  [upvote-button upvote-score]
  upvote-score
  {:inspect-data true :history true})

(defonce downvote-score (atom 20))

(defcard-rg downvote-button
  "## A button for downvoting"
  [downvote-button downvote-score]
  downvote-score
  {:inspect-data true :history true})
