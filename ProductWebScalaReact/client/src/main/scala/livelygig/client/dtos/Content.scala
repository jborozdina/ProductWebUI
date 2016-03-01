package livelygig.client.dtos

import java.beans.Expression
import java.util.Date

import boopickle.Default._
import upickle.Js

/**
  * Created by shubham.k on 12/14/2015.
  */
/**
  * Represents content within a request/response to API endpoints.
  */
sealed trait Content {

}
case class CreateUser(email: String, password: String, jsonBlob: Map[String, String], createBTCWallet: Boolean)
  extends Content

case class CreateUserResponse() /*extends Content*/

case class ConfirmEmail(token: String) extends Content

case class ConfirmEmailResponse(agentURI: String) /*extends Content*/
case class ErrorResponse(reason: String)

case class InitializeSession(agentURI: String)  extends Content

case class InitializeSessionResponse(sessionURI: String,listOfAliases: Seq[String],defaultAlias: String,listOfLabels: Seq[String],listOfConnections:Seq[Connection],
                                     lastActiveLabel:String,jsonBlob:Map[String, String]
                                     /*reason : Option[String]*/)  /*extends Content*/
case class InitializeSessionErrorResponse(reason : Option[String])
case class ConnectionProfileResponse(sessionURI: String, connection: Connection, jsonBlob: String
                                    /* ,name: Option[String]*/)  /*extends Content*/
case class ProjectsResponse(sessionURI: String, pageOfPosts: Seq[String], connection: Connection,
                            filter : String)

//case class JsonBlobModel(name: String, imgSrc: String)

case class Connection (source: String, label: String, target: String) /*extends Content*/

case class PageOfPosts(id : String, `type` : String,description : String,summary : String,
                       postedDate: String, broadcastDate: String, startDate: String, endDate: String, currency: String,
                       location: String, isPayoutInPieces: String, skills: Seq[Skills], posterId: String, canForward: String,
                       referents: Seq[Referents], contractType: String, budget: Float)
case class Skills (skillId : String, skillName: String)
case class Referents(referentId: String,referentName: String )

case class SessionPing(sessionURI: String) extends Content

case class SubscribeRequest (sessionUri: String, expression: Expression) extends Content

case class Expression (msgType: String, content: ExpressionContent)

case class ExpressionContent(cnxs: Seq[Connection], label: String)

object Content {
  implicit val requestContentPickler: Pickler[Content] = generatePickler[Content]
}

