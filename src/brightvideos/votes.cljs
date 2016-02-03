(ns brightvideos.votes
  (:require
    [reagent.core :as reagent :refer [atom cursor]]
    [devcards.core])
  (:require-macros [devcards.core :as dc :refer [defcard-rg]]))

; Upvote button
(defn upvote-button [score]
  [:a {:on-click #(swap! score inc)}
    [:i.fa.fa-plus]])

; Downvote button
(defn downvote-button [score]
  [:a {:on-click #(swap! score dec)}
    [:i.fa.fa-minus]])

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
