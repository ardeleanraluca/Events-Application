classDiagram
direction BT
class events
class favorite_events
class halls
class organizers
class roles {
varchar(255) name
integer id
}
class tickets
class user_accounts
class users

events  -->  halls : hall_id:id
events  -->  organizers : organizer_id:id
favorite_events  -->  events : event_id:id
favorite_events  -->  users : user_id:id
organizers  -->  user_accounts : account_id:id
tickets  -->  events : event_id:id
user_accounts  -->  roles : role_id:id
users  -->  user_accounts : account_id:id
